package com.schwarckstudio.lionfitness.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.ExerciseRepository
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.Equipment
import com.schwarckstudio.lionfitness.core.model.MuscleGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedEquipment = MutableStateFlow<List<Equipment>>(emptyList())
    val selectedEquipment = _selectedEquipment.asStateFlow()

    private val _selectedMuscles = MutableStateFlow<List<MuscleGroup>>(emptyList())
    val selectedMuscles = _selectedMuscles.asStateFlow()

    val filteredExercises = combine(
        _exercises,
        _searchQuery,
        _selectedEquipment,
        _selectedMuscles
    ) { exercises, query, equipment, muscles ->
        exercises.filter { exercise ->
            val matchesQuery = exercise.name.contains(query, ignoreCase = true)
            val matchesEquipment = equipment.isEmpty() || equipment.contains(exercise.equipment)
            val matchesMuscle = muscles.isEmpty() || muscles.contains(exercise.primaryMuscle)
            matchesQuery && matchesEquipment && matchesMuscle
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadExercises()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            exerciseRepository.getExercisesFlow().collect { exercises ->
                _exercises.value = exercises
            }
        }
    }

    fun getExercise(id: String): Exercise? {
        return _exercises.value.find { it.id == id }
    }
    
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
    
    fun toggleEquipmentFilter(equipment: Equipment) {
        _selectedEquipment.value = if (_selectedEquipment.value.contains(equipment)) {
            _selectedEquipment.value - equipment
        } else {
            _selectedEquipment.value + equipment
        }
    }
    
    fun toggleMuscleFilter(muscle: MuscleGroup) {
        _selectedMuscles.value = if (_selectedMuscles.value.contains(muscle)) {
            _selectedMuscles.value - muscle
        } else {
            _selectedMuscles.value + muscle
        }
    }
    
    fun clearFilters() {
        _selectedEquipment.value = emptyList()
        _selectedMuscles.value = emptyList()
        _searchQuery.value = ""
    }
    
    fun saveCustomExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.saveExercise(exercise)
        }
    }
}
