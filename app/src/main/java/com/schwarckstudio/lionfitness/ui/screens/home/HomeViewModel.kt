package com.schwarckstudio.lionfitness.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository
import com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

import com.schwarckstudio.lionfitness.core.data.repository.UserRepository
import kotlinx.coroutines.flow.flow

@HiltViewModel
class HomeViewModel @Inject constructor(
    workoutRepository: WorkoutRepository,
    routineRepository: RoutineRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val workoutsFlow = workoutRepository.getWorkoutsFlow()
    private val routinesFlow = routineRepository.getRoutinesFlow()
    private val userFlow = flow { emit(userRepository.getCurrentUser()) }

    val uiState: StateFlow<HomeUiState> = combine(
        workoutsFlow,
        routinesFlow,
        userFlow
    ) { workouts, routines, user ->
        val recentWorkouts = workouts.sortedByDescending { it.startTime }.take(3)
        val weeklyGoal = 5 // Hardcoded for now
        val workoutsThisWeek = countWorkoutsThisWeek(workouts)
        
        val workoutDays = workouts.map { 
            it.startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() 
        }.distinct()
        
        HomeUiState(
            recentWorkouts = recentWorkouts,
            routines = routines, // Showing all routines for now as "Favorites"
            weeklyGoal = weeklyGoal,
            weeklyProgress = workoutsThisWeek,
            workoutDays = workoutDays,
            userName = user?.displayName ?: "Lion",
            userProfileUrl = user?.photoUrl
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    private fun countWorkoutsThisWeek(workouts: List<WorkoutLog>): Int {
        val now = LocalDate.now()
        val startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            
        return workouts.count { it.startTime.toDate().toInstant().isAfter(startOfWeek) }
    }
}

data class HomeUiState(
    val recentWorkouts: List<WorkoutLog> = emptyList(),
    val routines: List<Routine> = emptyList(),
    val weeklyGoal: Int = 5,
    val weeklyProgress: Int = 0,
    val workoutDays: List<LocalDate> = emptyList(),
    val userName: String = "",
    val userProfileUrl: String? = null
)
