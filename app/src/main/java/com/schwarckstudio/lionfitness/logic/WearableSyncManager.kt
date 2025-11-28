package com.schwarckstudio.lionfitness.logic

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.core.model.dto.toDTO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WearableSyncManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val routineRepository: RoutineRepository,
    private val exerciseRepository: com.schwarckstudio.lionfitness.core.data.repository.ExerciseRepository
) {

    private val dataClient = Wearable.getDataClient(context)
    private val gson = Gson()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    companion object {
        private const val ROUTINES_DATA_PATH = "/routines"
        private const val EXERCISES_DATA_PATH = "/exercises"
    }

    init {
        startSync()
    }

    private fun startSync() {
        scope.launch {
            routineRepository.getRoutinesFlow().collect { routines ->
                syncRoutines(routines)
            }
        }
        scope.launch {
            exerciseRepository.getExercisesFlow().collect { exercises ->
                syncExercises(exercises)
            }
        }
    }

    private suspend fun syncExercises(exercises: List<com.schwarckstudio.lionfitness.core.model.Exercise>) {
        try {
            val json = gson.toJson(exercises)
            val putDataMapRequest = PutDataMapRequest.create(EXERCISES_DATA_PATH)
            putDataMapRequest.dataMap.putString("exercises_list", json)
            putDataMapRequest.dataMap.putLong("timestamp", System.currentTimeMillis())
            val request = putDataMapRequest.asPutDataRequest()
            request.setUrgent()
            dataClient.putDataItem(request).await()
            Log.d("WearableSyncManager", "Synced ${exercises.size} exercises to Wear OS")
        } catch (e: Exception) {
            Log.e("WearableSyncManager", "Failed to sync exercises", e)
        }
    }

    private suspend fun syncRoutines(routines: List<Routine>) {
        try {
            // Convert to DTOs if necessary, or just serialize directly if Routine is serializable/clean
            // Routine contains domain objects, maybe better to map to DTOs if we have them, 
            // but for now let's try direct serialization or map to a simplified structure.
            // Routine has RoutineExercise which is fine.
            
            // To be safe and consistent with other parts, let's use Gson.
            val json = gson.toJson(routines)
            
            val putDataMapRequest = PutDataMapRequest.create(ROUTINES_DATA_PATH)
            putDataMapRequest.dataMap.putString("routines_list", json)
            putDataMapRequest.dataMap.putLong("timestamp", System.currentTimeMillis())
            
            val request = putDataMapRequest.asPutDataRequest()
            request.setUrgent()
            
            dataClient.putDataItem(request).await()
            Log.d("WearableSyncManager", "Synced ${routines.size} routines to Wear OS")
        } catch (e: Exception) {
            Log.e("WearableSyncManager", "Failed to sync routines", e)
        }
    }
    
    // We can also add a method to force sync
    fun forceSync() {
        scope.launch {
            val routines = routineRepository.getRoutines()
            syncRoutines(routines)
        }
    }
}
