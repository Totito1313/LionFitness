package com.schwarckstudio.lionfitness.core.model

import com.google.firebase.Timestamp

// --- Workout Log ---
data class WorkoutLog(
    val id: String = "",
    val userId: String = "",
    val startTime: Timestamp = Timestamp.now(),
    val endTime: Timestamp? = null,
    val name: String = "",
    val description: String = "",
    val totalVolume: Double = 0.0,
    val totalSets: Int = 0,
    val totalReps: Int = 0,
    val records: Int = 0,
    val calories: Double = 0.0,
    val avgHeartRate: Int = 0,
    val distance: Double = 0.0,
    val muscleSplit: Map<String, Double> = emptyMap(), // e.g., "chest" -> 0.4
    val exercises: List<WorkoutExercise> = emptyList()
)

data class WorkoutExercise(
    val exerciseId: String = "",
    val exerciseName: String = "", // Denormalized for easier display
    val sets: List<WorkoutSet> = emptyList(),
    val personalRecords: List<RecordType> = emptyList(), // New field
    val supersetId: String? = null,
    val notes: String = ""
)

data class WorkoutSet(
    val weight: Double = 0.0,
    val reps: Int = 0,
    val rpe: Double? = null,
    val distance: Double? = null,
    val duration: Int? = null,
    val type: SetType = SetType.NORMAL,
    val completed: Boolean = false,
    val isPersonalRecord: Boolean = false // New field
)

enum class SetType {
    NORMAL, WARMUP, FAILURE, DROP_SET
}

enum class RecordType {
    WEIGHT, VOLUME, REPS
}

// --- Routines ---
data class Routine(
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val description: String = "",
    val notes: String = "",
    val exercises: List<RoutineExercise> = emptyList(),
    val tags: List<String> = emptyList(),
    val difficulty: String = "Intermedio",
    val lastUsed: Long = 0,
    val isFavorite: Boolean = false,
    val color: Long = 0xFF4C6EF5 // Default Blue
)

data class RoutineExercise(
    val exerciseId: String = "",
    val exerciseName: String = "",
    val notes: String = "",
    val templateSets: List<RoutineSetTemplate> = emptyList()
)

data class RoutineSetTemplate(
    val type: SetType = SetType.NORMAL,
    val targetReps: String = "", // e.g. "8-12"
    val targetWeight: String = "" // e.g. "20kg"
)
