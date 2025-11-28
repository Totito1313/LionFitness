package com.schwarckstudio.lionfitness.wear.data

import android.content.Context
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.dto.WorkoutDTO
import com.schwarckstudio.lionfitness.core.model.dto.toDTO
import com.schwarckstudio.lionfitness.wear.data.local.WearWorkoutDatabase
import com.schwarckstudio.lionfitness.wear.data.local.WearWorkoutEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class WearLocalWorkoutRepository(private val context: Context) {

    private val dao = WearWorkoutDatabase.getDatabase(context).wearWorkoutDao()
    private val dataClient = Wearable.getDataClient(context)
    private val gson = Gson()

    suspend fun saveWorkout(workout: WorkoutLog) {
        val dto = workout.toDTO()
        val json = gson.toJson(dto)
        val entity = WearWorkoutEntity(
            id = workout.id,
            name = workout.name,
            startTime = workout.startTime?.seconds ?: 0L,
            endTime = workout.endTime?.seconds ?: 0L,
            exercisesJson = json,
            isSynced = false,
            calories = 0, // TODO: Pass calories
            avgHeartRate = workout.avgHeartRate
        )
        dao.insertWorkout(entity)
    }

    suspend fun updateWorkoutName(id: String, name: String) {
        dao.updateName(id, name)
    }

    suspend fun syncUnsyncedWorkouts() {
        val unsynced = dao.getUnsyncedWorkouts()
        unsynced.forEach { entity ->
            try {
                // Send to phone
                val putDataMapRequest = PutDataMapRequest.create("/finished_workout")
                putDataMapRequest.dataMap.putString("workout_data", entity.exercisesJson)
                // We might need to update the name in the JSON if it was changed locally
                // But for now, let's assume the JSON is the source of truth for exercises
                // and we send the name separately or update the JSON.
                // Let's update the JSON with the new name if needed.
                
                // Deserialize, update name, serialize back
                val dto = gson.fromJson(entity.exercisesJson, WorkoutDTO::class.java)
                val updatedDto = dto.copy(name = entity.name)
                val updatedJson = gson.toJson(updatedDto)

                putDataMapRequest.dataMap.putString("workout_data", updatedJson)
                putDataMapRequest.dataMap.putLong("timestamp", System.currentTimeMillis())
                // Add a unique ID to the path or data to force a change event if needed, 
                // but timestamp usually handles it. 
                // To prevent duplicates on phone, the phone should check the workout ID inside the JSON.
                
                val request = putDataMapRequest.asPutDataRequest()
                request.setUrgent()
                dataClient.putDataItem(request).await()

                // Mark as synced
                dao.markAsSynced(entity.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    suspend fun getAllWorkouts(): List<WearWorkoutEntity> {
        return dao.getAllWorkouts()
    }
}
