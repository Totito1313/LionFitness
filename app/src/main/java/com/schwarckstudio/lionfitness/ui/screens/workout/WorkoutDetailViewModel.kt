package com.schwarckstudio.lionfitness.ui.screens.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WorkoutDetailUiState(
    val workout: WorkoutLog? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val workoutId: String = checkNotNull(savedStateHandle["workoutId"])
    private val _uiState = MutableStateFlow(WorkoutDetailUiState(isLoading = true))
    val uiState: StateFlow<WorkoutDetailUiState> = _uiState.asStateFlow()

    init {
        loadWorkout()
    }

    private fun loadWorkout() {
        viewModelScope.launch {
            val workout = workoutRepository.getWorkout(workoutId)
            _uiState.value = WorkoutDetailUiState(workout = workout, isLoading = false)
        }
    }

    fun updateWorkout(workout: WorkoutLog) {
        viewModelScope.launch {
            workoutRepository.saveWorkout(workout)
            _uiState.value = _uiState.value.copy(workout = workout)
        }
    }
}
