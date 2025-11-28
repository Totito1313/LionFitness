package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface WorkoutRepository {
    suspend fun getWorkouts(): List<WorkoutLog>
    fun getWorkoutsFlow(): Flow<List<WorkoutLog>>
    suspend fun saveWorkout(workout: WorkoutLog)
    suspend fun getWorkout(workoutId: String): WorkoutLog?
    suspend fun deleteWorkout(workoutId: String)
}

@OptIn(ExperimentalCoroutinesApi::class)
class WorkoutRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : WorkoutRepository {

    override suspend fun getWorkouts(): List<WorkoutLog> {
        val uid = auth.currentUser?.uid ?: return emptyList()
        val snapshot = firestore.collection("workout_logs")
            .whereEqualTo("userId", uid)
            .get()
            .await()
        return snapshot.toObjects(WorkoutLog::class.java)
    }

    override fun getWorkoutsFlow(): Flow<List<WorkoutLog>> {
        val authFlow = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser?.uid) }
            auth.addAuthStateListener(listener)
            trySend(auth.currentUser?.uid)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

        return authFlow.flatMapLatest { uid ->
            val userId = uid ?: "test_user"
            firestore.collection("workout_logs")
                .whereEqualTo("userId", userId)
                .snapshotFlow()
        }
    }

    override suspend fun saveWorkout(workout: WorkoutLog) {
        val uid = auth.currentUser?.uid ?: return
        // Ensure userId is set
        val workoutToSave = if (workout.userId.isEmpty()) workout.copy(userId = uid) else workout
        
        if (workoutToSave.id.isEmpty()) {
            firestore.collection("workout_logs").add(workoutToSave).await()
        } else {
            firestore.collection("workout_logs").document(workoutToSave.id).set(workoutToSave).await()
        }
    }

    override suspend fun getWorkout(workoutId: String): WorkoutLog? {
        return try {
            val document = firestore.collection("workout_logs").document(workoutId).get().await()
            document.toObject(WorkoutLog::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteWorkout(workoutId: String) {
        try {
            firestore.collection("workout_logs").document(workoutId).delete().await()
        } catch (e: Exception) {
            // Handle error or log it
        }
    }


    private fun com.google.firebase.firestore.Query.snapshotFlow(): Flow<List<WorkoutLog>> = callbackFlow {
        val listener = addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(emptyList())
                return@addSnapshotListener
            }
            val workouts = snapshot?.toObjects(WorkoutLog::class.java) ?: emptyList()
            trySend(workouts)
        }
        awaitClose { listener.remove() }
    }
}
