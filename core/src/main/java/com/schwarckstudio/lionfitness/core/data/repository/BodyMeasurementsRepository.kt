package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.schwarckstudio.lionfitness.core.model.BodyMeasurementLog
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface BodyMeasurementsRepository {
    suspend fun getMeasurementLogs(): List<BodyMeasurementLog>
    fun getMeasurementLogsFlow(): Flow<List<BodyMeasurementLog>>
    suspend fun saveMeasurementLog(log: BodyMeasurementLog)
    suspend fun getLatestBodyMeasurement(): BodyMeasurementLog?
}

class BodyMeasurementsRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : BodyMeasurementsRepository {

    override suspend fun getMeasurementLogs(): List<BodyMeasurementLog> {
        val uid = auth.currentUser?.uid ?: return emptyList()
        val snapshot = firestore.collection("body_measurements")
            .whereEqualTo("userId", uid)
            .get()
            .await()
        return snapshot.toObjects(BodyMeasurementLog::class.java).sortedByDescending { it.date }
    }

    override fun getMeasurementLogsFlow(): Flow<List<BodyMeasurementLog>> = callbackFlow {
        var firestoreListener: com.google.firebase.firestore.ListenerRegistration? = null
        
        val authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val uid = firebaseAuth.currentUser?.uid
            firestoreListener?.remove()
            
            if (uid == null) {
                trySend(emptyList())
            } else {
                firestoreListener = firestore.collection("body_measurements")
                    .whereEqualTo("userId", uid)
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) {
                            trySend(emptyList())
                            return@addSnapshotListener
                        }
                        val logs = snapshot?.toObjects(BodyMeasurementLog::class.java) ?: emptyList()
                        val sortedLogs = logs.sortedByDescending { it.date }
                        trySend(sortedLogs)
                    }
            }
        }
        
        auth.addAuthStateListener(authListener)
        
        awaitClose {
            auth.removeAuthStateListener(authListener)
            firestoreListener?.remove()
        }
    }

    override suspend fun saveMeasurementLog(log: BodyMeasurementLog) {
        val uid = auth.currentUser?.uid ?: return
        val logToSave = if (log.userId.isEmpty()) log.copy(userId = uid) else log

        if (logToSave.id.isEmpty()) {
            firestore.collection("body_measurements").add(logToSave).await()
        } else {
            firestore.collection("body_measurements").document(logToSave.id).set(logToSave).await()
        }
    }

    override suspend fun getLatestBodyMeasurement(): BodyMeasurementLog? {
        val uid = auth.currentUser?.uid ?: return null
        val snapshot = firestore.collection("body_measurements")
            .whereEqualTo("userId", uid)
            .orderBy("date", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
        return snapshot.toObjects(BodyMeasurementLog::class.java).firstOrNull()
    }
}
