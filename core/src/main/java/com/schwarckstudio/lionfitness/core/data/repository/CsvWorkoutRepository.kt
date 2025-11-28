package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.Timestamp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.schwarckstudio.lionfitness.core.data.csv.CsvManager
import com.schwarckstudio.lionfitness.core.model.WorkoutExercise
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.WorkoutSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class CsvWorkoutRepository @Inject constructor(
    private val csvManager: CsvManager
) : WorkoutRepository {

    private val _workoutsFlow = MutableStateFlow<List<WorkoutLog>>(emptyList())
    private val fileName = "workouts.csv"
    private val gson = Gson()

    init {
        loadWorkouts()
    }

    private fun loadWorkouts() {
        val data = csvManager.readCsv(fileName)
        
        // Group by workout ID (or start time + title if ID is missing) to reconstruct workouts
        val workouts = data.groupBy { it["start_time"] ?: "" }.mapNotNull { (_, rows) ->
            if (rows.isEmpty()) return@mapNotNull null
            
            val firstRow = rows.first()
            val startTimeStr = firstRow["start_time"] ?: return@mapNotNull null
            val startTime = try {
                Timestamp(Date(startTimeStr.toLongOrNull() ?: System.currentTimeMillis()))
            } catch (e: Exception) { Timestamp.now() }

            val endTime = try {
                val time = firstRow["end_time"]?.toLongOrNull()
                if (time != null) Timestamp(Date(time)) else null
            } catch (e: Exception) { null }

            // Reconstruct exercises
            val exercisesMap = rows.groupBy { it["exercise_title"] ?: "" }
            val exercises = exercisesMap.mapNotNull { (exerciseTitle, setRows) ->
                if (exerciseTitle.isEmpty()) return@mapNotNull null
                
                val firstSetRow = setRows.first()
                val sets = setRows.mapIndexed { index, row ->
                    WorkoutSet(
                        weight = row["weight_kg"]?.toDoubleOrNull() ?: 0.0,
                        reps = row["reps"]?.toIntOrNull() ?: 0,
                        rpe = row["rpe"]?.toDoubleOrNull(),
                        distance = row["distance_km"]?.toDoubleOrNull(),
                        duration = row["duration_seconds"]?.toIntOrNull(),
                        completed = true, // Assumed completed if in history
                        type = try {
                            com.schwarckstudio.lionfitness.core.model.SetType.valueOf(row["set_type"] ?: "NORMAL")
                        } catch (e: Exception) { com.schwarckstudio.lionfitness.core.model.SetType.NORMAL }
                    )
                }

                WorkoutExercise(
                    exerciseId = UUID.randomUUID().toString(), // Generate new ID as it's not in CSV
                    exerciseName = exerciseTitle,
                    sets = sets,
                    supersetId = firstSetRow["superset_id"]?.takeIf { it.isNotEmpty() },
                    notes = firstSetRow["exercise_notes"] ?: ""
                )
            }

            WorkoutLog(
                id = UUID.randomUUID().toString(), // Generate new ID as it's not in CSV
                userId = "user", // Default
                startTime = startTime,
                endTime = endTime,
                name = firstRow["title"] ?: "Workout",
                description = firstRow["description"] ?: "",
                totalVolume = 0.0, // Recalculate if needed
                totalSets = rows.size,
                totalReps = 0, // Recalculate if needed
                records = 0,
                calories = 0.0,
                distance = 0.0,
                muscleSplit = emptyMap(),
                exercises = exercises
            )
        }.sortedByDescending { it.startTime }
        
        _workoutsFlow.value = workouts
    }

    override suspend fun getWorkouts(): List<WorkoutLog> {
        return _workoutsFlow.value
    }

    override fun getWorkoutsFlow(): Flow<List<WorkoutLog>> {
        return _workoutsFlow.asStateFlow()
    }

    override suspend fun saveWorkout(workout: WorkoutLog) {
        val currentWorkouts = _workoutsFlow.value.toMutableList()
        val workoutToSave = if (workout.id.isEmpty()) workout.copy(id = UUID.randomUUID().toString()) else workout
        
        val index = currentWorkouts.indexOfFirst { it.id == workoutToSave.id }
        if (index != -1) {
            currentWorkouts[index] = workoutToSave
        } else {
            currentWorkouts.add(workoutToSave)
        }

        val sortedWorkouts = currentWorkouts.sortedByDescending { it.startTime }
        _workoutsFlow.value = sortedWorkouts
        saveToCsv(sortedWorkouts)
    }

    override suspend fun getWorkout(workoutId: String): WorkoutLog? {
        return _workoutsFlow.value.find { it.id == workoutId }
    }

    override suspend fun deleteWorkout(workoutId: String) {
        val currentWorkouts = _workoutsFlow.value.toMutableList()
        val removed = currentWorkouts.removeIf { it.id == workoutId }
        if (removed) {
            val sortedWorkouts = currentWorkouts.sortedByDescending { it.startTime }
            _workoutsFlow.value = sortedWorkouts
            saveToCsv(sortedWorkouts)
        }
    }

    private fun saveToCsv(workouts: List<WorkoutLog>) {
        val flattenedData = mutableListOf<Map<String, String>>()

        workouts.forEach { workout ->
            workout.exercises.forEach { exercise ->
                exercise.sets.forEachIndexed { index, set ->
                    flattenedData.add(
                        mapOf(
                            "title" to workout.name,
                            "start_time" to workout.startTime.toDate().time.toString(),
                            "end_time" to (workout.endTime?.toDate()?.time?.toString() ?: ""),
                            "description" to workout.description,
                            "exercise_title" to exercise.exerciseName,
                            "superset_id" to (exercise.supersetId ?: ""),
                            "exercise_notes" to exercise.notes,
                            "set_index" to index.toString(),
                            "set_type" to set.type.name,
                            "weight_kg" to set.weight.toString(),
                            "reps" to set.reps.toString(),
                            "distance_km" to (set.distance?.toString() ?: ""),
                            "duration_seconds" to (set.duration?.toString() ?: ""),
                            "rpe" to (set.rpe?.toString() ?: "")
                        )
                    )
                }
            }
        }
        csvManager.writeCsv(fileName, flattenedData)
    }
}
