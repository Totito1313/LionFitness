package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository
import com.schwarckstudio.lionfitness.core.model.Routine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RoutineDetailUiState(
    val routine: Routine? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class RoutineDetailViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val routineId: String = checkNotNull(savedStateHandle["routineId"])
    private val _uiState = MutableStateFlow(RoutineDetailUiState(isLoading = true))
    val uiState: StateFlow<RoutineDetailUiState> = _uiState.asStateFlow()

    init {
        loadRoutine()
    }

    private fun loadRoutine() {
        viewModelScope.launch {
            val routine = routineRepository.getRoutine(routineId)
            _uiState.value = RoutineDetailUiState(routine = routine, isLoading = false)
        }
    }
}
