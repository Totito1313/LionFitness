package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schwarckstudio.lionfitness.core.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UserRepository {
    suspend fun getCurrentUser(): User?
    fun getUserFlow(): Flow<User?>
    suspend fun saveUser(user: User)
    fun signOut()
}

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    @dagger.hilt.android.qualifiers.ApplicationContext private val context: android.content.Context
) : UserRepository {

    private val prefs by lazy {
        context.getSharedPreferences("user_prefs", android.content.Context.MODE_PRIVATE)
    }

    override suspend fun getCurrentUser(): User? {
        val uid = auth.currentUser?.uid ?: return null
        val snapshot = firestore.collection("users").document(uid).get().await()
        val user = snapshot.toObject(User::class.java)
        return user?.copy(photoUrl = getLocalPhotoUrl(uid) ?: user.photoUrl)
    }

    override fun getUserFlow(): Flow<User?> = callbackFlow {
        var firestoreListener: com.google.firebase.firestore.ListenerRegistration? = null
        var prefsListener: android.content.SharedPreferences.OnSharedPreferenceChangeListener? = null
        
        val authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val authUid = firebaseAuth.currentUser?.uid
            val email = firebaseAuth.currentUser?.email
            
            // Fallback to offline mode if no auth
            val uid = authUid ?: getOfflineUserId()
            
            android.util.Log.d("UserRepository", "getUserFlow: authUid=$authUid, fallbackUid=$uid, email=$email")
            
            if (authUid == null) {
                // Offline mode - use SharedPreferences with listener for changes
                android.util.Log.d("UserRepository", "getUserFlow: Offline mode, loading from SharedPreferences")
                
                // Emit initial value
                val offlineUser = getOfflineUser(uid)
                val localPhoto = getLocalPhotoUrl(uid)
                trySend(offlineUser?.copy(
                    photoUrl = localPhoto ?: offlineUser.photoUrl,
                    uid = uid
                ))
                
                // Listen for changes in offline user data
                val listener = android.content.SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                    if (key == "offline_user_$uid" || key == "local_photo_$uid") {
                        android.util.Log.d("UserRepository", "getUserFlow: Offline prefs changed, re-emitting")
                        val updatedUser = getOfflineUser(uid)
                        val updatedPhoto = getLocalPhotoUrl(uid)
                        trySend(updatedUser?.copy(
                            photoUrl = updatedPhoto ?: updatedUser?.photoUrl ?: "",
                            uid = uid
                        ))
                    }
                }
                prefsListener = listener
                prefs.registerOnSharedPreferenceChangeListener(listener)
                
                firestoreListener?.remove()
            } else {
                // Online mode - use Firestore
                firestoreListener?.remove()
                prefsListener?.let { prefs.unregisterOnSharedPreferenceChangeListener(it) }
                prefsListener = null
                
                firestoreListener = firestore.collection("users").document(authUid).addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        android.util.Log.e("UserRepository", "getUserFlow: error", error)
                        return@addSnapshotListener
                    }
                    
                    val localPhoto = getLocalPhotoUrl(authUid)
                    android.util.Log.d("UserRepository", "getUserFlow: localPhoto=$localPhoto")
                    
                    if (snapshot != null && snapshot.exists()) {
                        val user = snapshot.toObject(User::class.java)
                        android.util.Log.d("UserRepository", "getUserFlow: Firestore user=$user")
                        trySend(user?.copy(
                            photoUrl = localPhoto ?: user?.photoUrl ?: "",
                            email = if (user?.email.isNullOrEmpty()) email ?: "" else user?.email ?: ""
                        ))
                    } else {
                        // No Firestore document, create from Auth
                        android.util.Log.d("UserRepository", "getUserFlow: No Firestore doc, creating from Auth")
                        val user = User(
                            uid = authUid,
                            email = email ?: "",
                            photoUrl = localPhoto ?: ""
                        )
                        trySend(user)
                    }
                }
            }
        }
        
        auth.addAuthStateListener(authListener)
        
        awaitClose {
            auth.removeAuthStateListener(authListener)
            firestoreListener?.remove()
            prefsListener?.let { prefs.unregisterOnSharedPreferenceChangeListener(it) }
        }
    }

    override suspend fun saveUser(user: User) {
        val authUid = auth.currentUser?.uid
        val uid = authUid ?: getOfflineUserId()
        
        android.util.Log.d("UserRepository", "saveUser: authUid=$authUid, fallbackUid=$uid, user=$user")
        
        // Check if photoUrl is a local file path
        if (user.photoUrl.startsWith("file://") || user.photoUrl.contains("/files/")) {
            android.util.Log.d("UserRepository", "saveUser: Local photo detected, saving to SharedPreferences")
            saveLocalPhotoUrl(uid, user.photoUrl)
        }
        
        if (authUid == null) {
            // Offline mode - save to SharedPreferences
            android.util.Log.d("UserRepository", "saveUser: Offline mode, saving to SharedPreferences")
            saveOfflineUser(uid, user.copy(
                uid = uid,
                photoUrl = "" // Don't save local path in serialized user
            ))
        } else {
            // Online mode - save to Firestore
            val userToSave = if (user.photoUrl.startsWith("file://") || user.photoUrl.contains("/files/")) {
                user.copy(photoUrl = "") // Don't send local path to Firestore
            } else {
                user
            }
            
            android.util.Log.d("UserRepository", "saveUser: Online mode, saving to Firestore")
            firestore.collection("users").document(authUid).set(userToSave).await()
        }
    }

    override fun signOut() {
        auth.signOut()
    }
    
    private fun getOfflineUserId(): String {
        // Use Android ID as stable device identifier
        val androidId = android.provider.Settings.Secure.getString(
            context.contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        )
        return "offline_$androidId"
    }
    
    private fun getOfflineUser(uid: String): User? {
        val json = prefs.getString("offline_user_$uid", null) ?: return null
        return try {
            // Simple JSON parsing for User object
            val displayName = json.substringAfter("\"displayName\":\"").substringBefore("\"")
            val email = json.substringAfter("\"email\":\"").substringBefore("\"")
            User(
                uid = uid,
                email = email.takeIf { it.isNotEmpty() } ?: "",
                displayName = displayName.takeIf { it.isNotEmpty() } ?: ""
            )
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "getOfflineUser: error parsing", e)
            null
        }
    }
    
    private fun saveOfflineUser(uid: String, user: User) {
        // Simple JSON serialization
        val json = "{\"uid\":\"${user.uid}\",\"email\":\"${user.email}\",\"displayName\":\"${user.displayName}\"}"
        prefs.edit().putString("offline_user_$uid", json).apply()
        android.util.Log.d("UserRepository", "saveOfflineUser: uid=$uid, json=$json")
    }
    
    private fun getLocalPhotoUrl(uid: String): String? {
        val url = prefs.getString("local_photo_$uid", null)
        android.util.Log.d("UserRepository", "getLocalPhotoUrl: uid=$uid, url=$url")
        return url
    }
    
    private fun saveLocalPhotoUrl(uid: String, url: String) {
        android.util.Log.d("UserRepository", "saveLocalPhotoUrl: uid=$uid, url=$url")
        prefs.edit().putString("local_photo_$uid", url).apply()
    }
}
