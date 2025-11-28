package com.schwarckstudio.lionfitness.logic

import com.google.firebase.Timestamp
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.core.model.WorkoutExercise
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.WorkoutSet
import com.schwarckstudio.lionfitness.core.model.RecordType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

data class PrEvent(
    val exerciseName: String,
    val type: RecordType,
    val value: String
)

@Singleton
class ActiveWorkoutManager @Inject constructor(
    private val workoutRepository: com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository,
    private val settingsRepository: com.schwarckstudio.lionfitness.core.data.repository.SettingsRepository,
    private val wearableManager: WearableManager,
    @dagger.hilt.android.qualifiers.ApplicationContext private val context: android.content.Context
) {

    private val _currentWorkout = MutableStateFlow<WorkoutLog?>(null)
    val currentWorkout: StateFlow<WorkoutLog?> = _currentWorkout.asStateFlow()

    private val _prEvents = MutableSharedFlow<PrEvent>()
    val prEvents: SharedFlow<PrEvent> = _prEvents.asSharedFlow()

    val heartRate: StateFlow<Int> = wearableManager.heartRate
    
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    val isWatchConnected: StateFlow<Boolean> = wearableManager.connectedNodes
        .map { it.isNotEmpty() }
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), false)

    private val _caloriesBurned = MutableStateFlow(0)
    val caloriesBurned: StateFlow<Int> = _caloriesBurned.asStateFlow()

    private val _workoutDuration = MutableStateFlow(0L)
    val workoutDuration: StateFlow<Long> = _workoutDuration.asStateFlow()

    private var workoutTimerJob: Job? = null
    private var calorieTimerJob: Job? = null
    private val heartRateSamples = mutableListOf<Int>()

    init {
        scope.launch {
            wearableManager.heartRate.collect { hr ->
                if (hr > 0) {
                    heartRateSamples.add(hr)
                }
            }
        }

        scope.launch {
            wearableManager.events.collect { event ->
                when (event) {
                    is WearableEvent.ToggleSet -> {
                        toggleSetComplete(event.exerciseId, event.setIndex, event.isCompleted)
                    }
                    is WearableEvent.UpdateSet -> {
                        updateSet(event.exerciseId, event.setIndex, event.weight, event.reps, event.rpe)
                    }
                    is WearableEvent.FinishWorkout -> {
                        // Just finish the workout locally to trigger the Summary screen.
                        // The UI will observe `finishedWorkout` and navigate.
                        finishWorkout()
                    }
                    is WearableEvent.RequestState -> {
                        _currentWorkout.value?.let { workout ->
                            wearableManager.sendStartWorkoutMessage(workout.id)
                            wearableManager.sendWorkoutState(workout)
                        }
                    }
                    is WearableEvent.ReplaceExercise -> {
                        replaceExercise(event.oldExerciseId, event.newExerciseId)
                    }
                    is WearableEvent.FinishedWorkoutReceived -> {
                        // Received a finished workout from the watch (standalone mode)
                        // Check for duplicates before saving
                        scope.launch {
                            val existingWorkouts = workoutRepository.getWorkouts()
                            val isDuplicate = existingWorkouts.any { it.id == event.workout.id }
                            
                            if (!isDuplicate) {
                                workoutRepository.saveWorkout(event.workout)
                                // We might want to show the summary screen
                                _finishedWorkout.value = event.workout
                            } else {
                                android.util.Log.d("ActiveWorkoutManager", "Duplicate workout received: ${event.workout.id}, ignoring.")
                            }
                        }
                    }
                }
            }
        }
    }

    private var exerciseHistory: Map<String, List<WorkoutExercise>> = emptyMap()

    suspend fun startWorkout(routine: Routine?) {
        // Prevent overwriting an active workout if one exists
        if (_currentWorkout.value != null) {
            return
        }

        val workoutId = UUID.randomUUID().toString()
        val pastWorkouts = workoutRepository.getWorkouts().sortedByDescending { it.startTime }
        
        // Populate history for quick lookup
        exerciseHistory = pastWorkouts.flatMap { it.exercises }
            .groupBy { it.exerciseId }

        heartRateSamples.clear()
        startCalorieTimer()

        val exercises = routine?.exercises?.map { routineExercise ->
            // Find last performance of this exercise
            val lastLog = pastWorkouts.firstOrNull { log ->
                log.exercises.any { it.exerciseId == routineExercise.exerciseId }
            }
            
            val lastExerciseData = lastLog?.exercises?.find { it.exerciseId == routineExercise.exerciseId }
            
            val sets = if (lastExerciseData != null && lastExerciseData.sets.isNotEmpty()) {
                // Use sets from last session, but reset completion status
                lastExerciseData.sets.map { it.copy(completed = false, isPersonalRecord = false) }
            } else {
                // Fallback to template or default
                if (routineExercise.templateSets.isNotEmpty()) {
                    routineExercise.templateSets.map { template ->
                        WorkoutSet(
                            weight = template.targetWeight.replace("kg", "").toDoubleOrNull() ?: 0.0,
                            reps = template.targetReps.split("-").firstOrNull()?.toIntOrNull() ?: 0,
                            type = template.type
                        )
                    }
                } else {
                    listOf(WorkoutSet(weight = 0.0, reps = 0))
                }
            }

            WorkoutExercise(
                exerciseId = routineExercise.exerciseId,
                exerciseName = routineExercise.exerciseName,
                sets = sets,
                notes = routineExercise.notes
            )
        } ?: emptyList()

        val workout = WorkoutLog(
            id = workoutId,
            userId = "", // Will be set by repository or auth manager
            startTime = Timestamp.now(),
            name = routine?.name ?: "Entrenamiento Libre",
            exercises = exercises
        )

        _currentWorkout.value = workout
        startWorkoutTimer()
        
        // Notify watch
        wearableManager.sendStartWorkoutMessage(workoutId)
        wearableManager.sendWorkoutState(workout)
        
        // Start Foreground Service
        startService()
    }

    suspend fun addSet(exerciseId: String) {
        val current = _currentWorkout.value ?: return
        val updatedExercises = current.exercises.map { exercise ->
            if (exercise.exerciseId == exerciseId) {
                val newSet = WorkoutSet(weight = 0.0, reps = 0)
                exercise.copy(sets = exercise.sets + newSet)
            } else {
                exercise
            }
        }
        _currentWorkout.value = current.copy(exercises = updatedExercises)
        _currentWorkout.value?.let { wearableManager.sendWorkoutState(it) }
    }

    suspend fun updateSet(exerciseId: String, setIndex: Int, weight: Double, reps: Int, rpe: Int?) {
        val current = _currentWorkout.value ?: return
        val updatedExercises = current.exercises.map { exercise ->
            if (exercise.exerciseId == exerciseId) {
                val updatedSets = exercise.sets.toMutableList()
                if (setIndex in updatedSets.indices) {
                    updatedSets[setIndex] = updatedSets[setIndex].copy(weight = weight, reps = reps, rpe = rpe?.toDouble())
                }
                exercise.copy(sets = updatedSets)
            } else {
                exercise
            }
        }
        _currentWorkout.value = current.copy(exercises = updatedExercises)
        _currentWorkout.value?.let { wearableManager.sendWorkoutState(it) }
    }

    suspend fun toggleSetComplete(exerciseId: String, setIndex: Int, isCompleted: Boolean) {
        val current = _currentWorkout.value ?: return
        var prEventToEmit: PrEvent? = null

        val updatedExercises = current.exercises.map { exercise ->
            if (exercise.exerciseId == exerciseId) {
                val updatedSets = exercise.sets.toMutableList()
                if (setIndex in updatedSets.indices) {
                    val set = updatedSets[setIndex]
                    var isPr = set.isPersonalRecord
                    
                    if (isCompleted && !set.completed) {
                        // Just completed, check for PR
                        val history = exerciseHistory[exerciseId] ?: emptyList()
                        val maxWeightHistory = history.flatMap { it.sets }.maxOfOrNull { it.weight } ?: 0.0
                        
                        if (set.weight > maxWeightHistory && set.weight > 0) {
                            isPr = true
                            prEventToEmit = PrEvent(exercise.exerciseName, RecordType.WEIGHT, "${set.weight} kg")
                        }
                    } else if (!isCompleted) {
                        // Unchecking, remove PR status if it was one? 
                        // Ideally yes, but for now let's just keep it simple. 
                        // Actually if we uncheck, we should probably re-evaluate or just clear it.
                        isPr = false
                    }

                    updatedSets[setIndex] = updatedSets[setIndex].copy(completed = isCompleted, isPersonalRecord = isPr)
                }
                exercise.copy(sets = updatedSets)
            } else {
                exercise
            }
        }
        _currentWorkout.value = current.copy(exercises = updatedExercises)
        _currentWorkout.value?.let { wearableManager.sendWorkoutState(it) }
        
        if (settingsRepository.livePrNotificationsEnabled.value) {
            prEventToEmit?.let { _prEvents.emit(it) }
        }
    }

    suspend fun replaceExercise(oldExerciseId: String, newExerciseId: String) {
        val current = _currentWorkout.value ?: return
        val updatedExercises = current.exercises.map { exercise ->
            if (exercise.exerciseId == oldExerciseId) {
                WorkoutExercise(
                    exerciseId = newExerciseId,
                    sets = listOf(WorkoutSet(weight = 0.0, reps = 0))
                )
            } else {
                exercise
            }
        }
        _currentWorkout.value = current.copy(exercises = updatedExercises)
        _currentWorkout.value?.let { wearableManager.sendWorkoutState(it) }
    }

    suspend fun addExercise(exerciseId: String) {
        val current = _currentWorkout.value ?: return
        
        // Fetch exercise name/details. 
        // We can try to find it in history or we might need to fetch it from repo if it's new.
        // For now, let's try history or just use ID as name (and let UI resolve it).
        // Ideally we should inject ExerciseRepository here or pass the name.
        // Let's assume the UI passes the ID and we rely on the UI to display the name correctly 
        // OR we fetch it. Since we are in Manager, let's fetch it properly if possible.
        // But we only have WorkoutRepository.
        // Let's just add it with empty name and let the UI/ViewModel fill it or resolve it.
        // Actually, WorkoutExercise needs a name.
        
        // Better approach: The ViewModel has access to ExerciseRepository. 
        // Let's make this function take the name too, or just ID and we find name from history.
        
        val history = exerciseHistory[exerciseId]
        val name = history?.firstOrNull()?.exerciseName ?: "Ejercicio" 
        // If not in history, we might have a problem with name. 
        // But let's proceed. The ViewModel can update it or we can pass it.
        
        val newExercise = WorkoutExercise(
            exerciseId = exerciseId,
            exerciseName = name,
            sets = listOf(WorkoutSet(weight = 0.0, reps = 0))
        )
        
        _currentWorkout.value = current.copy(exercises = current.exercises + newExercise)
        _currentWorkout.value?.let { wearableManager.sendWorkoutState(it) }
    }
    
    private val _finishedWorkout = MutableStateFlow<WorkoutLog?>(null)
    val finishedWorkout: StateFlow<WorkoutLog?> = _finishedWorkout.asStateFlow()

    suspend fun finishWorkout(): WorkoutLog? {
        val current = _currentWorkout.value ?: return null
        
        var totalVolume = 0.0
        var totalSets = 0
        var totalReps = 0
        
        current.exercises.forEach { exercise ->
            exercise.sets.forEach { set ->
                if (set.completed) {
                    totalVolume += set.weight * set.reps
                    totalSets++
                    totalReps += set.reps
                }
            }
        }
        
        val endTime = Timestamp.now()
        val durationSeconds = endTime.seconds - current.startTime.seconds
        // val calories = (durationSeconds / 60.0) * 5.0 // Estimate 5 kcal/min -> Use tracked calories
        val calories = _caloriesBurned.value.toDouble()
        val avgHeartRate = if (heartRateSamples.isNotEmpty()) heartRateSamples.average().toInt() else 0
        
        // Calculate PRs (Full check)
        val pastWorkouts = workoutRepository.getWorkouts()
        
        val finishedWorkoutWithPRs = calculatePersonalRecords(current, pastWorkouts).copy(
            endTime = endTime,
            totalVolume = totalVolume,
            totalSets = totalSets,
            totalReps = totalReps,

            calories = calories,
            avgHeartRate = avgHeartRate
        )
        
        // Do NOT clear current workout yet. Wait for save or discard.
        _finishedWorkout.value = finishedWorkoutWithPRs
        
        // Notify Watch
        wearableManager.sendStopWorkoutMessage()
        
        stopCalorieTimer()
        stopService()
        
        return finishedWorkoutWithPRs
    }

    private fun startCalorieTimer() {
        calorieTimerJob?.cancel()
        _caloriesBurned.value = 0
        calorieTimerJob = scope.launch {
            while (isActive) {
                delay(60000) // 1 minute
                _caloriesBurned.value += 5 // 5 kcal per minute estimate
            }
        }
    }

    private fun stopCalorieTimer() {
        calorieTimerJob?.cancel()
    }

    private fun startWorkoutTimer() {
        workoutTimerJob?.cancel()
        _workoutDuration.value = 0
        workoutTimerJob = scope.launch {
            val startTime = System.currentTimeMillis()
            while (isActive) {
                val elapsed = (System.currentTimeMillis() - startTime) / 1000
                _workoutDuration.value = elapsed
                delay(1000)
            }
        }
    }

    private fun stopWorkoutTimer() {
        workoutTimerJob?.cancel()
    }

    fun getPreviousSet(exerciseId: String, setIndex: Int): WorkoutSet? {
        // exerciseHistory is populated from sorted workouts (descending)
        // So the first entry in the list for an exercise is the most recent one.
        val history = exerciseHistory[exerciseId] ?: return null
        // We want the most recent session's exercise data.
        // Since flatMap preserves order of workouts, and we sorted workouts desc, 
        // the first WorkoutExercise in the list is from the most recent workout.
        return history.firstOrNull()?.sets?.getOrNull(setIndex)
    }

    private fun calculatePersonalRecords(current: WorkoutLog, history: List<WorkoutLog>): WorkoutLog {
        val updatedExercises = current.exercises.map { currentExercise ->
            val pastExercises = history.flatMap { it.exercises }
                .filter { it.exerciseId == currentExercise.exerciseId }
            
            if (pastExercises.isEmpty()) {
                val maxWeightSet = currentExercise.sets.maxByOrNull { it.weight }
                val updatedSets = currentExercise.sets.map { set ->
                    if (set == maxWeightSet && set.completed) set.copy(isPersonalRecord = true) else set
                }
                return@map currentExercise.copy(sets = updatedSets, personalRecords = listOf(RecordType.WEIGHT))
            }

            val maxWeightHistory = pastExercises.flatMap { it.sets }.maxOfOrNull { it.weight } ?: 0.0
            
            // 1. Check for Weight PR (1RM)
            var weightPR = false
            val updatedSets = currentExercise.sets.map { set ->
                if (set.completed && set.weight > maxWeightHistory) {
                    weightPR = true
                    set.copy(isPersonalRecord = true)
                } else {
                    set
                }
            }

            // 2. Check for Volume PR (Session Volume)
            val currentVolume = currentExercise.sets.filter { it.completed }.sumOf { it.weight * it.reps }
            val maxSessionVolumeHistory = history.map { log ->
                log.exercises.filter { it.exerciseId == currentExercise.exerciseId }
                    .flatMap { it.sets }
                    .sumOf { it.weight * it.reps }
            }.maxOrNull() ?: 0.0
            
            val volumePR = currentVolume > maxSessionVolumeHistory

            val newRecords = mutableListOf<RecordType>()
            if (weightPR) newRecords.add(RecordType.WEIGHT)
            if (volumePR) newRecords.add(RecordType.VOLUME)

            currentExercise.copy(sets = updatedSets, personalRecords = newRecords)
        }
        
        val totalRecords = updatedExercises.sumOf { it.personalRecords.size + it.sets.count { set -> set.isPersonalRecord } }
        return current.copy(exercises = updatedExercises, records = totalRecords)
    }

    suspend fun discardActiveWorkout() {
        _currentWorkout.value = null
        _finishedWorkout.value = null
        wearableManager.sendStopWorkoutMessage()
        stopCalorieTimer()
        stopService()
        stopWorkoutTimer() // Added this line
    }

    fun clearFinishedWorkout() {
        _finishedWorkout.value = null
    }

    fun updateFinishedWorkout(workout: WorkoutLog) {
        _finishedWorkout.value = workout
    }
    
    private fun startService() {
        try {
            val intent = android.content.Intent(context, WorkoutService::class.java)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        } catch (e: Exception) {
            android.util.Log.e("ActiveWorkoutManager", "Failed to start service", e)
        }
    }

    private fun stopService() {
        try {
            val intent = android.content.Intent(context, WorkoutService::class.java)
            context.stopService(intent)
        } catch (e: Exception) {
            android.util.Log.e("ActiveWorkoutManager", "Failed to stop service", e)
        }
    }
}
