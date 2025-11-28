package com.schwarckstudio.lionfitness.core.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.schwarckstudio.lionfitness.core.data.csv.CsvManager
import com.schwarckstudio.lionfitness.core.model.Equipment
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.ExerciseType
import com.schwarckstudio.lionfitness.core.model.MuscleGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CsvExerciseRepository @Inject constructor(
    private val csvManager: CsvManager
) : ExerciseRepository {

    private val fileName = "exercises.csv"
    private val headers = listOf(
        "id", "name", "equipment", "primaryMuscle", "secondaryMuscles", 
        "type", "imageUrl", "isCustom", "userId"
    )
    private val gson = Gson()

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())

    init {
        loadExercises()
    }

    private fun loadExercises() {
        val rows = csvManager.readCsv(fileName)
        if (rows.isEmpty()) {
            seedInitialExercises()
        } else {
            val loadedExercises = rows.mapNotNull { row ->
                try {
                    val secondaryMusclesJson = row["secondaryMuscles"] ?: "[]"
                    val secondaryMusclesType = object : TypeToken<List<MuscleGroup>>() {}.type
                    val secondaryMuscles: List<MuscleGroup> = gson.fromJson(secondaryMusclesJson, secondaryMusclesType)

                    Exercise(
                        id = row["id"] ?: "",
                        name = row["name"] ?: "",
                        equipment = try { Equipment.valueOf(row["equipment"] ?: "NONE") } catch (e: Exception) { Equipment.NONE },
                        primaryMuscle = try { MuscleGroup.valueOf(row["primaryMuscle"] ?: "UNKNOWN") } catch (e: Exception) { MuscleGroup.UNKNOWN },
                        secondaryMuscles = secondaryMuscles,
                        type = try { ExerciseType.valueOf(row["type"] ?: "WEIGHT_AND_REPS") } catch (e: Exception) { ExerciseType.WEIGHT_AND_REPS },
                        imageUrl = row["imageUrl"] ?: "",
                        isCustom = row["isCustom"]?.toBoolean() ?: false,
                        userId = row["userId"]
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
            _exercises.value = loadedExercises
        }
    }

    private fun seedInitialExercises() {
        val initialExercises = listOf(
            Exercise(
                id = "bench_press",
                name = "Press de Banca",
                equipment = Equipment.BARBELL,
                primaryMuscle = MuscleGroup.CHEST,
                secondaryMuscles = listOf(MuscleGroup.TRICEPS, MuscleGroup.ANTERIOR_DELT),
                type = ExerciseType.WEIGHT_AND_REPS,
                imageUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/bench_press.png"
            ),
            Exercise(
                id = "squat",
                name = "Sentadilla",
                equipment = Equipment.BARBELL,
                primaryMuscle = MuscleGroup.QUADS,
                secondaryMuscles = listOf(MuscleGroup.GLUTES, MuscleGroup.LOWER_BACK),
                type = ExerciseType.WEIGHT_AND_REPS,
                imageUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/squat.png"
            ),
            Exercise(
                id = "deadlift",
                name = "Peso Muerto",
                equipment = Equipment.BARBELL,
                primaryMuscle = MuscleGroup.BACK,
                secondaryMuscles = listOf(MuscleGroup.HAMSTRINGS, MuscleGroup.GLUTES),
                type = ExerciseType.WEIGHT_AND_REPS,
                imageUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/deadlift.png"
            ),
            Exercise(
                id = "pull_up",
                name = "Dominadas",
                equipment = Equipment.BODYWEIGHT,
                primaryMuscle = MuscleGroup.LATS,
                secondaryMuscles = listOf(MuscleGroup.BICEPS),
                type = ExerciseType.BODYWEIGHT_REPS,
                imageUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/pull_up.png"
            ),
             Exercise(
                id = "overhead_press",
                name = "Press Militar",
                equipment = Equipment.BARBELL,
                primaryMuscle = MuscleGroup.SHOULDERS,
                secondaryMuscles = listOf(MuscleGroup.TRICEPS),
                type = ExerciseType.WEIGHT_AND_REPS,
                imageUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/overhead_press.png"
            )
        )
        _exercises.value = initialExercises
        saveToCsv()
    }

    private fun saveToCsv() {
        val data = _exercises.value.map { exercise ->
            mapOf(
                "id" to exercise.id,
                "name" to exercise.name,
                "equipment" to exercise.equipment.name,
                "primaryMuscle" to exercise.primaryMuscle.name,
                "secondaryMuscles" to gson.toJson(exercise.secondaryMuscles),
                "type" to exercise.type.name,
                "imageUrl" to exercise.imageUrl,
                "isCustom" to exercise.isCustom.toString(),
                "userId" to (exercise.userId ?: "")
            )
        }
        csvManager.writeCsv(fileName, data)
    }

    override suspend fun getExercises(): List<Exercise> {
        return _exercises.value
    }

    override fun getExercisesFlow(): Flow<List<Exercise>> = _exercises

    override suspend fun getExercise(id: String): Exercise? {
        return _exercises.value.find { it.id == id }
    }

    override suspend fun saveExercise(exercise: Exercise) {
        val currentList = _exercises.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == exercise.id }
        if (index != -1) {
            currentList[index] = exercise
        } else {
            currentList.add(exercise)
        }
        _exercises.value = currentList
        saveToCsv()
    }

    suspend fun deleteExercise(exerciseId: String) {
        val currentList = _exercises.value.toMutableList()
        currentList.removeAll { it.id == exerciseId }
        _exercises.value = currentList
        saveToCsv()
    }
    
    suspend fun searchExercises(query: String): List<Exercise> {
        return _exercises.value.filter { 
            it.name.contains(query, ignoreCase = true) 
        }
    }
}
