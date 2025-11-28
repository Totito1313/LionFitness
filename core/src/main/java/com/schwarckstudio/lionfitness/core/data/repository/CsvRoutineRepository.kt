package com.schwarckstudio.lionfitness.core.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.schwarckstudio.lionfitness.core.data.csv.CsvManager
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.core.model.RoutineExercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

class CsvRoutineRepository @Inject constructor(
    private val csvManager: CsvManager
) : RoutineRepository {

    private val _routinesFlow = MutableStateFlow<List<Routine>>(emptyList())
    private val fileName = "routines.csv"
    private val gson = Gson()

    init {
        loadRoutines()
    }

    private fun loadRoutines() {
        val data = csvManager.readCsv(fileName)
        val routines = data.map { row ->
            val exercisesJson = row["exercises"] ?: "[]"
            val exercisesType = object : TypeToken<List<RoutineExercise>>() {}.type
            val exercises: List<RoutineExercise> = try {
                gson.fromJson(exercisesJson, exercisesType)
            } catch (e: Exception) {
                emptyList()
            }

            val tagsJson = row["tags"] ?: "[]"
            val tagsType = object : TypeToken<List<String>>() {}.type
            val tags: List<String> = try {
                gson.fromJson(tagsJson, tagsType)
            } catch (e: Exception) {
                emptyList()
            }

            Routine(
                id = row["id"] ?: "",
                userId = row["userId"] ?: "",
                name = row["name"] ?: "",
                description = row["description"] ?: "",
                notes = row["notes"] ?: "",
                exercises = exercises,
                tags = tags,
                difficulty = row["difficulty"] ?: "",
                lastUsed = row["lastUsed"]?.toLongOrNull() ?: 0L,
                isFavorite = row["isFavorite"]?.toBoolean() ?: false,
                color = row["color"]?.toLongOrNull() ?: 0L
            )
        }
        _routinesFlow.value = routines
    }

    override suspend fun getRoutines(): List<Routine> {
        return _routinesFlow.value
    }

    override fun getRoutinesFlow(): Flow<List<Routine>> {
        return _routinesFlow.asStateFlow()
    }

    override suspend fun saveRoutine(routine: Routine) {
        val currentRoutines = _routinesFlow.value.toMutableList()
        val routineToSave = if (routine.id.isEmpty()) routine.copy(id = UUID.randomUUID().toString()) else routine
        
        val index = currentRoutines.indexOfFirst { it.id == routineToSave.id }
        if (index != -1) {
            currentRoutines[index] = routineToSave
        } else {
            currentRoutines.add(routineToSave)
        }

        _routinesFlow.value = currentRoutines
        saveToCsv(currentRoutines)
    }

    override suspend fun getRoutine(routineId: String): Routine? {
        return _routinesFlow.value.find { it.id == routineId }
    }

    private fun saveToCsv(routines: List<Routine>) {
        val data = routines.map { routine ->
            mapOf(
                "id" to routine.id,
                "userId" to routine.userId,
                "name" to routine.name,
                "description" to routine.description,
                "notes" to routine.notes,
                "exercises" to gson.toJson(routine.exercises),
                "tags" to gson.toJson(routine.tags),
                "difficulty" to routine.difficulty,
                "lastUsed" to routine.lastUsed.toString(),
                "isFavorite" to routine.isFavorite.toString(),
                "color" to routine.color.toString()
            )
        }
        csvManager.writeCsv(fileName, data)
    }
}
