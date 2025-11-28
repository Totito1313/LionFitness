package com.schwarckstudio.lionfitness.wear.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.dto.WorkoutDTO
import com.schwarckstudio.lionfitness.core.model.dto.toDomain
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.schwarckstudio.lionfitness.core.model.dto.toDTO

class WearViewModel(application: Application) : AndroidViewModel(application), 
    DataClient.OnDataChangedListener, 
    MessageClient.OnMessageReceivedListener {

    var isWorkoutActive by mutableStateOf(false)
        private set
    var currentWorkout by mutableStateOf<WorkoutLog?>(null)
        private set
    var timerSeconds by mutableStateOf(0)
        private set
    var isTimerRunning by mutableStateOf(false)
        private set
    var restTimerSeconds by mutableStateOf(0)
        private set
    var isRestTimerRunning by mutableStateOf(false)
        private set
    private var restTimerJob: kotlinx.coroutines.Job? = null
    var calories by mutableStateOf(0)
        private set
    var heartRate by mutableStateOf(0)
        private set // Heart rate is updated from Activity via updateHeartRate

    private val gson = Gson()
    private val messageClient = Wearable.getMessageClient(application)
    private val dataClient = Wearable.getDataClient(application)
    private val nodeClient = Wearable.getNodeClient(application)

    private val routineRepository = com.schwarckstudio.lionfitness.wear.data.WearRoutineRepository(application)
    val routines = routineRepository.getRoutines()

    private val exerciseRepository = com.schwarckstudio.lionfitness.wear.data.WearExerciseRepository(application)
    val exercises = exerciseRepository.getExercises()

    var showExercisePicker by mutableStateOf(false)
        private set

    init {
        messageClient.addListener(this)
        dataClient.addListener(this)
        requestState()
    }

    private fun requestState() {
        viewModelScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()
                nodes.forEach { node ->
                    messageClient.sendMessage(node.id, "/request_state", byteArrayOf()).await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        messageClient.removeListener(this)
        dataClient.removeListener(this)
    }

    private val heartRateHistory = java.util.LinkedList<Int>()
    private val MAX_HR_HISTORY = 5

    fun updateHeartRate(hr: Int) {
        if (hr > 0) {
            heartRateHistory.add(hr)
            if (heartRateHistory.size > MAX_HR_HISTORY) {
                heartRateHistory.removeFirst()
            }
            heartRate = heartRateHistory.average().toInt()
            sendHeartRate(heartRate)
        }
    }

    private fun sendHeartRate(hr: Int) {
        viewModelScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()
                val message = hr.toString().toByteArray()
                nodes.forEach { node ->
                    messageClient.sendMessage(node.id, "/heart_rate", message).await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    var isPhoneWorkoutAvailable by mutableStateOf(false)
        private set

    override fun onMessageReceived(messageEvent: MessageEvent) {
        when (messageEvent.path) {
            "/start_workout" -> {
                // Phone started workout.
                // We DO NOT auto-start. We just notify that it's available.
                isPhoneWorkoutAvailable = true
                android.util.Log.d("WearViewModel", "Received /start_workout. Available: true")
                
                // Manually fetch data in case we missed the event
                viewModelScope.launch {
                    try {
                        val dataItemBuffer = dataClient.dataItems.await()
                        for (item in dataItemBuffer) {
                            if (item.uri.path?.endsWith("/active_workout") == true) {
                                val dataMap = DataMapItem.fromDataItem(item).dataMap
                                val json = dataMap.getString("workout_data")
                                if (json != null) {
                                    val dto = gson.fromJson(json, WorkoutDTO::class.java)
                                    currentWorkout = dto.toDomain()
                                    isPhoneWorkoutAvailable = true
                                }
                            }
                        }
                        dataItemBuffer.release()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            "/stop_workout" -> {
                isWorkoutActive = false
                currentWorkout = null
                isPhoneWorkoutAvailable = false
            }
            "/timer_state" -> {
                val parts = String(messageEvent.data).split("|")
                if (parts.size >= 2) {
                    timerSeconds = parts[0].toIntOrNull() ?: 0
                    isTimerRunning = parts[1].toBoolean()
                    if (parts.size >= 3) {
                        calories = parts[2].toIntOrNull() ?: 0
                    }
                }
            }
        }
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        dataEvents.forEach { event ->
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path?.endsWith("/active_workout") == true) {
                val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                val json = dataMap.getString("workout_data")
                if (json != null) {
                    try {
                        val dto = gson.fromJson(json, WorkoutDTO::class.java)
                        currentWorkout = dto.toDomain()
                        isPhoneWorkoutAvailable = true
                        android.util.Log.d("WearViewModel", "Workout data received. Available: true. Active: $isWorkoutActive")
                        // Do not auto-start: isWorkoutActive = true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        dataEvents.release()
    }

    fun syncPhoneWorkout() {
        if (currentWorkout != null) {
            isWorkoutActive = true
        } else {
            // Try fetching again
             viewModelScope.launch {
                try {
                    val dataItemBuffer = dataClient.dataItems.await()
                    for (item in dataItemBuffer) {
                        if (item.uri.path?.endsWith("/active_workout") == true) {
                            val dataMap = DataMapItem.fromDataItem(item).dataMap
                            val json = dataMap.getString("workout_data")
                            if (json != null) {
                                val dto = gson.fromJson(json, WorkoutDTO::class.java)
                                currentWorkout = dto.toDomain()
                                isWorkoutActive = true
                            }
                        }
                    }
                    dataItemBuffer.release()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
             }
        }
    }

    fun startStandaloneWorkout(routine: com.schwarckstudio.lionfitness.core.model.Routine?) {
        val workoutId = java.util.UUID.randomUUID().toString()
        val exercises = routine?.exercises?.map { routineExercise ->
            val sets = if (routineExercise.templateSets.isNotEmpty()) {
                routineExercise.templateSets.map { template ->
                    com.schwarckstudio.lionfitness.core.model.WorkoutSet(
                        weight = template.targetWeight.replace("kg", "").toDoubleOrNull() ?: 0.0,
                        reps = template.targetReps.split("-").firstOrNull()?.toIntOrNull() ?: 0,
                        type = template.type
                    )
                }
            } else {
                listOf(com.schwarckstudio.lionfitness.core.model.WorkoutSet(weight = 0.0, reps = 0))
            }

            com.schwarckstudio.lionfitness.core.model.WorkoutExercise(
                exerciseId = routineExercise.exerciseId,
                exerciseName = routineExercise.exerciseName,
                sets = sets
            )
        } ?: emptyList()

        currentWorkout = WorkoutLog(
            id = workoutId,
            userId = "", 
            startTime = com.google.firebase.Timestamp.now(),
            name = routine?.name ?: "Entrenamiento Libre",
            exercises = exercises
        )
        isWorkoutActive = true
        startTimer()
    }

    private var timerJob: kotlinx.coroutines.Job? = null

    private var accumulatedCalories = 0.0

    private fun startTimer() {
        timerJob?.cancel()
        isTimerRunning = true
        timerJob = viewModelScope.launch {
            while (isTimerRunning) {
                kotlinx.coroutines.delay(1000L)
                timerSeconds++
                
                // Calorie calculation (Keytel formula approximation for Male, 30yo, 75kg)
                // C/min = (-55.0969 + 0.6309 x HR + 0.1988 x Weight + 0.2017 x Age) / 4.184
                // Simplified: (-34 + 0.63 * HR) / 4.184
                val currentHr = if (heartRate > 0) heartRate else 80 // Default to 80 if no sensor data
                val kcalPerMin = ((-34 + (0.63 * currentHr)) / 4.184).coerceAtLeast(1.5) // Min burn rate
                val kcalPerSec = kcalPerMin / 60.0
                
                accumulatedCalories += kcalPerSec
                calories = accumulatedCalories.toInt()
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        isTimerRunning = false
    }

    fun toggleSet(exerciseId: String, setIndex: Int, isCompleted: Boolean) {
        // If standalone, update local state
        if (currentWorkout != null) {
            val updatedExercises = currentWorkout!!.exercises.map { exercise ->
                if (exercise.exerciseId == exerciseId) {
                    val updatedSets = exercise.sets.toMutableList()
                    if (setIndex in updatedSets.indices) {
                        updatedSets[setIndex] = updatedSets[setIndex].copy(completed = isCompleted)
                    }
                    exercise.copy(sets = updatedSets)
                } else {
                    exercise
                }
            }
            currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
        }

        // Also try to send to phone if connected (for mirroring)
        viewModelScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()
                if (nodes.isNotEmpty()) {
                    val message = "$exerciseId|$setIndex|$isCompleted"
                    nodes.forEach { node ->
                        messageClient.sendMessage(node.id, "/toggle_set", message.toByteArray()).await()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateSet(exerciseId: String, setIndex: Int, weight: Double, reps: Int, rpe: Int?, type: com.schwarckstudio.lionfitness.core.model.SetType) {
        // If standalone, update local state
        if (currentWorkout != null) {
            val updatedExercises = currentWorkout!!.exercises.map { exercise ->
                if (exercise.exerciseId == exerciseId) {
                    val updatedSets = exercise.sets.toMutableList()
                    if (setIndex in updatedSets.indices) {
                        updatedSets[setIndex] = updatedSets[setIndex].copy(
                            weight = weight, 
                            reps = reps, 
                            rpe = rpe?.toDouble(),
                            type = type
                        )
                    }
                    exercise.copy(sets = updatedSets)
                } else {
                    exercise
                }
            }
            currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
        }

        viewModelScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()
                if (nodes.isNotEmpty()) {
                    val message = "$exerciseId|$setIndex|$weight|$reps|${rpe ?: ""}|${type.name}"
                    nodes.forEach { node ->
                        messageClient.sendMessage(node.id, "/update_set", message.toByteArray()).await()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val localWorkoutRepository = com.schwarckstudio.lionfitness.wear.data.WearLocalWorkoutRepository(application)
    
    var showFinishScreen by mutableStateOf(false)
        private set
    
    // ... (existing code)

    fun finishWorkout() {
        // If standalone, we need to save locally first
        var workout = currentWorkout
        if (workout != null) {
            // Populate final stats
            workout = workout.copy(
                endTime = com.google.firebase.Timestamp.now(),
                calories = calories.toDouble(),
                avgHeartRate = heartRate, // Or calculate average from history if we tracked it fully
                // Duration is difference between start and end, but we have timerSeconds
                // Let's use timerSeconds as duration if we want active time
            )
            
            viewModelScope.launch {
                try {
                    // Save locally
                    localWorkoutRepository.saveWorkout(workout)
                    
                    // Attempt sync
                    localWorkoutRepository.syncUnsyncedWorkouts()
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        // Also send the simple finish signal for legacy/mirroring
        viewModelScope.launch {
            try {
                val nodes = nodeClient.connectedNodes.await()
                nodes.forEach { node ->
                    messageClient.sendMessage(node.id, "/finish_workout_request", byteArrayOf()).await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        
        isWorkoutActive = false
        // Do not clear currentWorkout yet, we need it for the finish screen
        // currentWorkout = null 
        showFinishScreen = true
    }

    fun onFinishScreenDismissed() {
        showFinishScreen = false
        isWorkoutActive = false
        currentWorkout = null
        stopTimer()
        // stopRestTimer() // This function is not defined in the provided code. Commenting out to avoid compilation error.
        timerSeconds = 0
        calories = 0
        accumulatedCalories = 0.0
    }

    fun forceSync() {
        viewModelScope.launch {
            localWorkoutRepository.syncUnsyncedWorkouts()
        }
    }
    
    fun updateWorkoutName(id: String, name: String) {
        viewModelScope.launch {
            localWorkoutRepository.updateWorkoutName(id, name)
            // After renaming, try to sync again to send the updated name
            localWorkoutRepository.syncUnsyncedWorkouts()
        }
    }

    fun toggleExercisePicker(show: Boolean) {
        showExercisePicker = show
    }



    fun addSet(exerciseId: String) {
        if (currentWorkout != null) {
            val updatedExercises = currentWorkout!!.exercises.map { exercise ->
                if (exercise.exerciseId == exerciseId) {
                    val lastSet = exercise.sets.lastOrNull()
                    val newSet = if (lastSet != null) {
                        lastSet.copy(completed = false) // Copy previous set values
                    } else {
                        com.schwarckstudio.lionfitness.core.model.WorkoutSet(weight = 0.0, reps = 0)
                    }
                    exercise.copy(sets = exercise.sets + newSet)
                } else {
                    exercise
                }
            }
            currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
        }
    }

    fun deleteSet(exerciseId: String, setIndex: Int) {
        if (currentWorkout != null) {
            val updatedExercises = currentWorkout!!.exercises.map { exercise ->
                if (exercise.exerciseId == exerciseId) {
                    val updatedSets = exercise.sets.toMutableList()
                    if (setIndex in updatedSets.indices) {
                        updatedSets.removeAt(setIndex)
                    }
                    exercise.copy(sets = updatedSets)
                } else {
                    exercise
                }
            }
            currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
        }
    }

    fun deleteExercise(exerciseIndex: Int) {
        if (currentWorkout != null) {
            val updatedExercises = currentWorkout!!.exercises.toMutableList()
            if (exerciseIndex in updatedExercises.indices) {
                updatedExercises.removeAt(exerciseIndex)
                currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
            }
        }
    }
    
    // For replacing, we store the INDEX of the exercise to be replaced
    var exerciseToReplaceIndex by mutableStateOf<Int?>(null)
        private set

    fun startReplaceExercise(exerciseIndex: Int) {
        exerciseToReplaceIndex = exerciseIndex
        showExercisePicker = true
    }

    fun addExerciseToWorkout(exercise: com.schwarckstudio.lionfitness.core.model.Exercise) {
        if (currentWorkout != null) {
            if (exerciseToReplaceIndex != null) {
                // Replace logic
                val index = exerciseToReplaceIndex!!
                if (index in currentWorkout!!.exercises.indices) {
                    val existing = currentWorkout!!.exercises[index]
                    val updatedExercises = currentWorkout!!.exercises.toMutableList()
                    updatedExercises[index] = com.schwarckstudio.lionfitness.core.model.WorkoutExercise(
                        exerciseId = exercise.id,
                        exerciseName = exercise.name,
                        sets = existing.sets, // Keep sets
                        supersetId = existing.supersetId,
                        notes = existing.notes
                    )
                    currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
                    
                    // Sync replacement to phone
                    viewModelScope.launch {
                        try {
                            val nodes = nodeClient.connectedNodes.await()
                            if (nodes.isNotEmpty()) {
                                val message = "${existing.exerciseId}|${exercise.id}"
                                nodes.forEach { node ->
                                    messageClient.sendMessage(node.id, "/replace_exercise", message.toByteArray()).await()
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                exerciseToReplaceIndex = null
            } else {
                // Add new logic
                val newExercise = com.schwarckstudio.lionfitness.core.model.WorkoutExercise(
                    exerciseId = exercise.id,
                    exerciseName = exercise.name,
                    sets = listOf(com.schwarckstudio.lionfitness.core.model.WorkoutSet(weight = 0.0, reps = 0))
                )
                val updatedExercises = currentWorkout!!.exercises + newExercise
                currentWorkout = currentWorkout!!.copy(exercises = updatedExercises)
            }
            showExercisePicker = false
        }
    }
}
