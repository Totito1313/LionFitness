package com.schwarckstudio.lionfitness.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestTimerManager @Inject constructor(
    private val wearableManager: WearableManager,
    private val activeWorkoutManager: ActiveWorkoutManager
) {

    private val _remainingSeconds = MutableStateFlow(0)
    val remainingSeconds: StateFlow<Int> = _remainingSeconds.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private var timerJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        scope.launch {
            activeWorkoutManager.caloriesBurned.collect { calories ->
                wearableManager.sendTimerState(_remainingSeconds.value, _isRunning.value, calories)
            }
        }
        
        scope.launch {
            wearableManager.events.collect { event ->
                if (event is WearableEvent.RequestState) {
                    wearableManager.sendTimerState(_remainingSeconds.value, _isRunning.value, activeWorkoutManager.caloriesBurned.value)
                }
            }
        }
    }

    fun startTimer(durationSeconds: Int) {
        stopTimer()
        _remainingSeconds.value = durationSeconds
        _isRunning.value = true
        
        scope.launch { 
            wearableManager.sendTimerState(durationSeconds, true, activeWorkoutManager.caloriesBurned.value) 
        }

        timerJob = scope.launch {
            while (_remainingSeconds.value > 0) {
                delay(1000L)
                _remainingSeconds.value -= 1
                wearableManager.sendTimerState(_remainingSeconds.value, true, activeWorkoutManager.caloriesBurned.value)
            }
            stopTimer()
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        _isRunning.value = false
        _remainingSeconds.value = 0
        scope.launch { 
            wearableManager.sendTimerState(0, false, activeWorkoutManager.caloriesBurned.value) 
        }
    }

    fun addTime(seconds: Int) {
        if (_isRunning.value) {
            _remainingSeconds.value += seconds
            scope.launch { 
                wearableManager.sendTimerState(_remainingSeconds.value, true, activeWorkoutManager.caloriesBurned.value) 
            }
        }
    }
    
    fun subtractTime(seconds: Int) {
        if (_isRunning.value) {
            _remainingSeconds.value = (_remainingSeconds.value - seconds).coerceAtLeast(0)
            scope.launch { 
                wearableManager.sendTimerState(_remainingSeconds.value, true, activeWorkoutManager.caloriesBurned.value) 
            }
        }
    }
}
