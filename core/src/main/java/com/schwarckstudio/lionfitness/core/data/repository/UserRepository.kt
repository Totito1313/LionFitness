package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schwarckstudio.lionfitness.core.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun saveUser(user: User)
    fun signOut()
}

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getCurrentUser(): User? {
        val uid = auth.currentUser?.uid ?: return null
        val snapshot = firestore.collection("users").document(uid).get().await()
        return snapshot.toObject(User::class.java)
    }

    override suspend fun saveUser(user: User) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("users").document(uid).set(user).await()
    }

    override fun signOut() {
        auth.signOut()
    }
}
