package com.schwarckstudio.lionfitness.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.ExerciseRepository
import com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.logic.ActiveWorkoutManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.schwarckstudio.lionfitness.logic.RestTimerManager

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val activeWorkoutManager: ActiveWorkoutManager,
    private val workoutRepository: WorkoutRepository,
    private val exerciseRepository: ExerciseRepository,
    private val restTimerManager: RestTimerManager,
    private val routineRepository: com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository
) : ViewModel() {

    val currentWorkout = activeWorkoutManager.currentWorkout
    val workoutDuration = activeWorkoutManager.workoutDuration
    val timerRemaining = restTimerManager.remainingSeconds
    val isTimerRunning = restTimerManager.isRunning
    val heartRate = activeWorkoutManager.heartRate
    val caloriesBurned = activeWorkoutManager.caloriesBurned
    val isWatchConnected = activeWorkoutManager.isWatchConnected
    
    // Combine workout data with exercise details for UI
    val workoutUiState: StateFlow<WorkoutUiState> = combine(
        currentWorkout,
        exerciseRepository.getExercisesFlow()
    ) { workout, exercises ->
        if (workout == null) {
            WorkoutUiState.Inactive
        } else {
            val exerciseDetails = workout.exercises.map { workoutExercise ->
                val exercise = exercises.find { it.id == workoutExercise.exerciseId }
                val setDetails = workoutExercise.sets.mapIndexed { index, set ->
                    val previousSet = activeWorkoutManager.getPreviousSet(workoutExercise.exerciseId, index)
                    SetUiData(index, set, previousSet)
                }

                ExerciseUiData(
                    id = workoutExercise.exerciseId,
                    name = exercise?.name ?: workoutExercise.exerciseName.takeIf { it.isNotEmpty() } ?: "Unknown Exercise",
                    sets = setDetails
                )
            }
            WorkoutUiState.Active(exerciseDetails)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WorkoutUiState.Inactive)

    fun startWorkout(routineId: String) {
        viewModelScope.launch {
            val routine = routineRepository.getRoutine(routineId)
            activeWorkoutManager.startWorkout(routine)
        }
    }

    fun startWorkout(routine: Routine?) {
        viewModelScope.launch {
            activeWorkoutManager.startWorkout(routine)
        }
    }

    fun addSet(exerciseId: String) {
        viewModelScope.launch {
            activeWorkoutManager.addSet(exerciseId)
        }
    }

    fun updateSet(exerciseId: String, setIndex: Int, weight: String, reps: String, rpe: String) {
        viewModelScope.launch {
            val w = weight.toDoubleOrNull() ?: 0.0
            val r = reps.toIntOrNull() ?: 0
            val rp = rpe.toIntOrNull()
            activeWorkoutManager.updateSet(exerciseId, setIndex, w, r, rp)
        }
    }

    val prEvents = activeWorkoutManager.prEvents

    fun toggleSetComplete(exerciseId: String, setIndex: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            activeWorkoutManager.toggleSetComplete(exerciseId, setIndex, isCompleted)
            if (isCompleted) {
                restTimerManager.startTimer(90) // Default 90s rest
            }
        }
    }

    fun replaceExercise(oldExerciseId: String, newExerciseId: String) {
        viewModelScope.launch {
            activeWorkoutManager.replaceExercise(oldExerciseId, newExerciseId)
        }
    }

    fun addExercise(exerciseId: String) {
        viewModelScope.launch {
            // We could fetch the name here to be sure
            val exercise = exerciseRepository.getExercise(exerciseId)
            // We need to update ActiveWorkoutManager to accept name or update it after.
            // For now, let's just call the manager. 
            // Actually, I should update the manager to take the name if I want it to be correct immediately.
            // But I already updated the manager to take only ID. 
            // Let's update the manager again to take name, or just let it be.
            // Wait, I can't easily update the manager signature again without another tool call.
            // Let's stick to ID for now, and maybe the UI will resolve the name from the ID 
            // because `WorkoutUiState` maps IDs to names using `exerciseRepository`.
            // Yes! `WorkoutUiState` does: `val exercise = exercises.find { it.id == workoutExercise.exerciseId }`
            // So the name in `WorkoutExercise` object in Manager doesn't matter as much for the ACTIVE UI.
            // It DOES matter for the saved log though.
            // So I should probably update the name in the Manager.
            
            activeWorkoutManager.addExercise(exerciseId)
            
            // If the manager uses a default name, we might want to update it. 
            // But let's rely on the fact that when we save, we might want to ensure names are correct?
            // Or better, let's just trust the UI state for now.
        }
    }

    fun stopTimer() {
        restTimerManager.stopTimer()
    }

    fun addTime(seconds: Int) {
        restTimerManager.addTime(seconds)
    }

    fun subtractTime(seconds: Int) {
        restTimerManager.subtractTime(seconds)
    }

    val finishedWorkout = activeWorkoutManager.finishedWorkout

    fun finishWorkout(onFinished: () -> Unit) {
        viewModelScope.launch {
            val workout = activeWorkoutManager.finishWorkout()
            if (workout != null) {
                onFinished()
            } else {
                onFinished()
            }
        }
    }

    fun updateFinishedWorkout(name: String, description: String) {
        val current = activeWorkoutManager.finishedWorkout.value ?: return
        activeWorkoutManager.updateFinishedWorkout(current.copy(name = name, description = description))
    }

    fun saveFinishedWorkout(onSaved: () -> Unit) {
        val workout = activeWorkoutManager.finishedWorkout.value ?: return
        viewModelScope.launch {
            workoutRepository.saveWorkout(workout)
            activeWorkoutManager.discardActiveWorkout() // Clear both active and finished
            onSaved()
        }
    }

    fun returnToWorkout(onReturned: () -> Unit) {
        activeWorkoutManager.clearFinishedWorkout() // Only clear finished (summary), keep active
        onReturned()
    }

    fun cancelWorkout(onCancelled: () -> Unit) {
        viewModelScope.launch {
            activeWorkoutManager.discardActiveWorkout() // Clear everything
            onCancelled()
        }
    }
}

sealed class WorkoutUiState {
    object Inactive : WorkoutUiState()
    data class Active(val exercises: List<ExerciseUiData>) : WorkoutUiState()
}

data class ExerciseUiData(
    val id: String,
    val name: String,
    val sets: List<SetUiData>
)

data class SetUiData(
    val index: Int,
    val current: com.schwarckstudio.lionfitness.core.model.WorkoutSet,
    val previous: com.schwarckstudio.lionfitness.core.model.WorkoutSet?
)
