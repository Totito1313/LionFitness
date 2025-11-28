package com.schwarckstudio.lionfitness.wear.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WearWorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WearWorkoutEntity)

    @Query("SELECT * FROM workouts WHERE isSynced = 0")
    suspend fun getUnsyncedWorkouts(): List<WearWorkoutEntity>

    @Query("UPDATE workouts SET isSynced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: String)

    @Query("UPDATE workouts SET name = :name WHERE id = :id")
    suspend fun updateName(id: String, name: String)
    
    @Query("SELECT * FROM workouts ORDER BY startTime DESC")
    suspend fun getAllWorkouts(): List<WearWorkoutEntity>
}
