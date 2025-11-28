package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schwarckstudio.lionfitness.core.model.Exercise
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ExerciseRepository {
    suspend fun getExercises(): List<Exercise>
    fun getExercisesFlow(): kotlinx.coroutines.flow.Flow<List<Exercise>>
    suspend fun saveExercise(exercise: Exercise)
    suspend fun getExercise(id: String): Exercise?
}

@OptIn(ExperimentalCoroutinesApi::class)
class ExerciseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ExerciseRepository {

    override suspend fun getExercises(): List<Exercise> {
        // Keep existing implementation for one-shot calls if needed, 
        // but UI should primarily use the Flow.
        val globalExercises = try {
            firestore.collection("global_exercises")
                .get()
                .await()
                .toObjects(Exercise::class.java)
        } catch (e: Exception) {
            emptyList()
        }

        val uid = auth.currentUser?.uid ?: "test_user"
        val customExercises = try {
            firestore.collection("users").document(uid).collection("user_custom_exercises")
                .get()
                .await()
                .toObjects(Exercise::class.java)
        } catch (e: Exception) {
            emptyList()
        }

        return globalExercises + customExercises
    }

    override fun getExercisesFlow(): kotlinx.coroutines.flow.Flow<List<Exercise>> {
        val authFlow = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser?.uid) }
            auth.addAuthStateListener(listener)
            trySend(auth.currentUser?.uid)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

        return authFlow.flatMapLatest { uid ->
            val globalFlow = firestore.collection("global_exercises").snapshotFlow()
            
            val userFlow = if (uid != null) {
                firestore.collection("users").document(uid).collection("user_custom_exercises").snapshotFlow()
            } else {
                // Fallback to test_user if needed, or empty
                firestore.collection("users").document("test_user").collection("user_custom_exercises").snapshotFlow()
            }
            
            combine(globalFlow, userFlow) { global, user -> global + user }
        }
    }

    override suspend fun saveExercise(exercise: Exercise) {
        val uid = auth.currentUser?.uid ?: "test_user"
        val exerciseToSave = exercise.copy(userId = uid, isCustom = true)
        
        val collection = firestore.collection("users").document(uid).collection("user_custom_exercises")
        
        try {
            if (exerciseToSave.id.isEmpty()) {
                collection.add(exerciseToSave).await()
            } else {
                collection.document(exerciseToSave.id).set(exerciseToSave).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getExercise(id: String): Exercise? {
        // Try global first
        try {
            val globalDoc = firestore.collection("global_exercises").document(id).get().await()
            if (globalDoc.exists()) {
                return globalDoc.toObject(Exercise::class.java)
            }
        } catch (e: Exception) {
            // Ignore
        }

        // Try custom
        val uid = auth.currentUser?.uid ?: "test_user"
        try {
            val customDoc = firestore.collection("users").document(uid).collection("user_custom_exercises").document(id).get().await()
            if (customDoc.exists()) {
                return customDoc.toObject(Exercise::class.java)
            }
        } catch (e: Exception) {
            // Ignore
        }
        
        return null
    }

    private fun com.google.firebase.firestore.Query.snapshotFlow(): kotlinx.coroutines.flow.Flow<List<Exercise>> = callbackFlow {
        val listener = addSnapshotListener { snapshot, error ->
            if (error != null) {
                // In case of error (e.g. permission denied), emit empty list or handle gracefully
                trySend(emptyList())
                return@addSnapshotListener
            }
            val exercises = snapshot?.toObjects(Exercise::class.java) ?: emptyList()
            trySend(exercises)
        }
        awaitClose { listener.remove() }
    }
}
