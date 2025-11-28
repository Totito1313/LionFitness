package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schwarckstudio.lionfitness.core.model.Routine
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface RoutineRepository {
    suspend fun getRoutines(): List<Routine>
    fun getRoutinesFlow(): kotlinx.coroutines.flow.Flow<List<Routine>>
    suspend fun saveRoutine(routine: Routine)
    suspend fun getRoutine(routineId: String): Routine?
}

@OptIn(ExperimentalCoroutinesApi::class)
class RoutineRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : RoutineRepository {

    override suspend fun getRoutines(): List<Routine> {
        val uid = auth.currentUser?.uid ?: "test_user"
        val snapshot = firestore.collection("routines")
            .whereEqualTo("userId", uid)
            .get()
            .await()
        return snapshot.toObjects(Routine::class.java)
    }

    override fun getRoutinesFlow(): kotlinx.coroutines.flow.Flow<List<Routine>> {
        val authFlow = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser?.uid) }
            auth.addAuthStateListener(listener)
            trySend(auth.currentUser?.uid)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

        return authFlow.flatMapLatest { uid ->
            val userId = uid ?: "test_user"
            firestore.collection("routines")
                .whereEqualTo("userId", userId)
                .snapshotFlow()
        }
    }

    override suspend fun saveRoutine(routine: Routine) {
        val uid = auth.currentUser?.uid ?: "test_user"
        val routineToSave = routine.copy(userId = uid)
        
        if (routineToSave.id.isEmpty()) {
            firestore.collection("routines").add(routineToSave).await()
        } else {
            firestore.collection("routines").document(routineToSave.id).set(routineToSave).await()
        }
    }

    override suspend fun getRoutine(routineId: String): Routine? {
        return try {
            val document = firestore.collection("routines").document(routineId).get().await()
            document.toObject(Routine::class.java)
        } catch (e: Exception) {
            null
        }
    }


    private fun com.google.firebase.firestore.Query.snapshotFlow(): kotlinx.coroutines.flow.Flow<List<Routine>> = callbackFlow {
        val listener = addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(emptyList())
                return@addSnapshotListener
            }
            val routines = snapshot?.toObjects(Routine::class.java) ?: emptyList()
            trySend(routines)
        }
        awaitClose { listener.remove() }
    }
}
