package com.schwarckstudio.lionfitness.core.model.dto

import com.google.firebase.Timestamp
import com.schwarckstudio.lionfitness.core.model.RecordType
import com.schwarckstudio.lionfitness.core.model.SetType
import com.schwarckstudio.lionfitness.core.model.WorkoutExercise
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.WorkoutSet
import java.util.Date

data class WorkoutDTO(
    val id: String,
    val userId: String,
    val startTime: Long,
    val endTime: Long?,
    val name: String,
    val description: String,
    val calories: Double = 0.0,
    val avgHeartRate: Int = 0,
    val exercises: List<WorkoutExerciseDTO>
)

data class WorkoutExerciseDTO(
    val exerciseId: String,
    val exerciseName: String,
    val sets: List<WorkoutSetDTO>,
    val supersetId: String?,
    val notes: String
)

data class WorkoutSetDTO(
    val weight: Double,
    val reps: Int,
    val rpe: Double?,
    val distance: Double?,
    val duration: Int?,
    val type: String, // Enum name
    val completed: Boolean
)

// Mappers

fun WorkoutLog.toDTO(): WorkoutDTO {
    return WorkoutDTO(
        id = this.id,
        userId = this.userId,
        startTime = this.startTime.toDate().time,
        endTime = this.endTime?.toDate()?.time,
        name = this.name,
        description = this.description,
        calories = this.calories,
        avgHeartRate = this.avgHeartRate,
        exercises = this.exercises.map { it.toDTO() }
    )
}

fun WorkoutExercise.toDTO(): WorkoutExerciseDTO {
    return WorkoutExerciseDTO(
        exerciseId = this.exerciseId,
        exerciseName = this.exerciseName,
        sets = this.sets.map { it.toDTO() },
        supersetId = this.supersetId,
        notes = this.notes
    )
}

fun WorkoutSet.toDTO(): WorkoutSetDTO {
    return WorkoutSetDTO(
        weight = this.weight,
        reps = this.reps,
        rpe = this.rpe,
        distance = this.distance,
        duration = this.duration,
        type = this.type.name,
        completed = this.completed
    )
}

fun WorkoutDTO.toDomain(): WorkoutLog {
    return WorkoutLog(
        id = this.id,
        userId = this.userId,
        startTime = Timestamp(Date(this.startTime)),
        endTime = this.endTime?.let { Timestamp(Date(it)) },
        name = this.name,
        description = this.description,
        calories = this.calories,
        avgHeartRate = this.avgHeartRate,
        exercises = this.exercises.map { it.toDomain() }
    )
}

fun WorkoutExerciseDTO.toDomain(): WorkoutExercise {
    return WorkoutExercise(
        exerciseId = this.exerciseId,
        exerciseName = this.exerciseName,
        sets = this.sets.map { it.toDomain() },
        supersetId = this.supersetId,
        notes = this.notes
    )
}

fun WorkoutSetDTO.toDomain(): WorkoutSet {
    return WorkoutSet(
        weight = this.weight,
        reps = this.reps,
        rpe = this.rpe,
        distance = this.distance,
        duration = this.duration,
        type = try { SetType.valueOf(this.type) } catch (e: Exception) { SetType.NORMAL },
        completed = this.completed
    )
}
