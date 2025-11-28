package com.schwarckstudio.lionfitness.ui

import androidx.lifecycle.ViewModel
import com.schwarckstudio.lionfitness.core.data.preferences.PreferencesManager
import com.schwarckstudio.lionfitness.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val activeWorkoutManager: com.schwarckstudio.lionfitness.logic.ActiveWorkoutManager
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination = _startDestination.asStateFlow()

    val finishedWorkout = activeWorkoutManager.finishedWorkout

    init {
        val isFirstRun = preferencesManager.isFirstRun
        _startDestination.value = if (isFirstRun) Screen.GetStarted.route else Screen.Home.route
    }

    fun completeOnboarding() {
        preferencesManager.isFirstRun = false
    }

    fun clearFinishedWorkout() {
        activeWorkoutManager.clearFinishedWorkout()
    }
}
