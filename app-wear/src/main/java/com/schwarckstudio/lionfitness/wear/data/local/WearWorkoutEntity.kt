package com.schwarckstudio.lionfitness.wear.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WearWorkoutEntity(
    @PrimaryKey val id: String,
    val name: String,
    val startTime: Long,
    val endTime: Long,
    val exercisesJson: String, // Serialized list of exercises
    val isSynced: Boolean = false,
    val calories: Int = 0,
    val avgHeartRate: Int = 0
)
