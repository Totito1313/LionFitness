package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository
import com.schwarckstudio.lionfitness.core.model.Routine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val workoutRepository: com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository,
    private val analyticsRepository: com.schwarckstudio.lionfitness.core.data.repository.AnalyticsRepository
) : ViewModel() {

    private val _routines = MutableStateFlow<List<Routine>>(emptyList())
    val routines: StateFlow<List<Routine>> = _routines.asStateFlow()

    private val _workoutLogs = MutableStateFlow<List<com.schwarckstudio.lionfitness.core.model.WorkoutLog>>(emptyList())
    val workoutLogs: StateFlow<List<com.schwarckstudio.lionfitness.core.model.WorkoutLog>> = _workoutLogs.asStateFlow()
    
    val globalRecords = analyticsRepository.getGlobalRecords()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), com.schwarckstudio.lionfitness.core.data.repository.GlobalRecords())

    val userStats = analyticsRepository.getUserStats()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), com.schwarckstudio.lionfitness.core.data.repository.UserStats(0, 0, 0.0, com.schwarckstudio.lionfitness.core.data.repository.WeeklyStats(0, 0, 0, 0.0, emptyMap())))

    init {
        loadRoutines()
        loadWorkoutLogs()
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            routineRepository.getRoutinesFlow().collect { routines ->
                _routines.value = routines
            }
        }
    }

    private fun loadWorkoutLogs() {
        viewModelScope.launch {
         val workoutLogs = workoutRepository.getWorkoutsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
            workoutLogs.collect { logs ->
                _workoutLogs.value = logs
            }
        }
    }

    fun deleteWorkout(workoutId: String) {
        viewModelScope.launch {
            workoutRepository.deleteWorkout(workoutId)
        }
    }
    fun saveRoutine(routine: Routine) {
        viewModelScope.launch {
            routineRepository.saveRoutine(routine)
        }
    }
}
