package com.schwarckstudio.lionfitness.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import com.schwarckstudio.lionfitness.wear.logic.WearWorkoutService

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var heartRateSensor: Sensor? = null
    private lateinit var viewModel: WearViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BODY_SENSORS)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.BODY_SENSORS), 1)
        }

        setContent {
            viewModel = viewModel()
            
            // Observe state from ViewModel
            val isWorkoutActive = viewModel.isWorkoutActive
            
            // Start/Stop service based on state
            // Start/Stop service based on state and update notification
            LaunchedEffect(isWorkoutActive, viewModel.timerSeconds, viewModel.currentWorkout) {
                if (isWorkoutActive) {
                    val intent = android.content.Intent(this@MainActivity, WearWorkoutService::class.java)
                    val title = viewModel.currentWorkout?.name ?: "Entrenamiento Activo"
                    val content = if (viewModel.isTimerRunning) {
                        "Descanso: ${formatTime(viewModel.timerSeconds)}"
                    } else {
                        "En curso"
                    }
                    intent.putExtra("title", title)
                    intent.putExtra("content", content)
                    
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        startForegroundService(intent)
                    } else {
                        startService(intent)
                    }
                } else {
                    stopService()
                }
            }

            val routines = viewModel.routines.collectAsState(initial = emptyList())
            val exercises = viewModel.exercises.collectAsState(initial = emptyList())

            WearApp(
                isWorkoutActive = isWorkoutActive, 
                currentWorkout = viewModel.currentWorkout, 
                timerSeconds = viewModel.timerSeconds, 
                isTimerRunning = viewModel.isTimerRunning, 
                restTimerSeconds = viewModel.restTimerSeconds,
                calories = viewModel.calories,
                heartRate = viewModel.heartRate,
                routines = routines.value,
                exercises = exercises.value,
                showExercisePicker = viewModel.showExercisePicker,
                onSetClick = viewModel::toggleSet,
                onUpdateSet = viewModel::updateSet,
                onFinishWorkout = viewModel::onFinishScreenDismissed,
                onStartStandaloneWorkout = viewModel::startStandaloneWorkout,
                onAddExerciseClick = { viewModel.toggleExercisePicker(true) },
                onExerciseSelected = viewModel::addExerciseToWorkout,
                onCancelExercisePicker = { viewModel.toggleExercisePicker(false) },
                isPhoneWorkoutAvailable = viewModel.isPhoneWorkoutAvailable,
                onSyncPhoneWorkout = viewModel::syncPhoneWorkout,
                showFinishScreen = viewModel.showFinishScreen,
                onForceSync = viewModel::forceSync,
                onUpdateWorkoutName = viewModel::updateWorkoutName,
                onAddSet = { exerciseId -> viewModel.addSet(exerciseId) },
                onDeleteSet = { exerciseId, setIndex -> viewModel.deleteSet(exerciseId, setIndex) },
                onReplaceExercise = { exerciseIndex -> viewModel.startReplaceExercise(exerciseIndex) },
                onDeleteExercise = { exerciseIndex -> viewModel.deleteExercise(exerciseIndex) }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        heartRateSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_HEART_RATE) {
            val hr = event.values[0].toInt()
            if (::viewModel.isInitialized) {
                viewModel.updateHeartRate(hr)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do nothing
    }

    private fun startService() {
        val intent = android.content.Intent(this, WearWorkoutService::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

    private fun stopService() {
        val intent = android.content.Intent(this, WearWorkoutService::class.java)
        stopService(intent)
    }
}

@Composable
fun WearApp(
    isWorkoutActive: Boolean, 
    currentWorkout: WorkoutLog?, 
    timerSeconds: Int, 
    isTimerRunning: Boolean, 
    restTimerSeconds: Int,
    calories: Int,
    heartRate: Int,
    routines: List<com.schwarckstudio.lionfitness.core.model.Routine>,
    exercises: List<com.schwarckstudio.lionfitness.core.model.Exercise>,
    showExercisePicker: Boolean,
    onSetClick: (String, Int, Boolean) -> Unit,
    onUpdateSet: (String, Int, Double, Int, Int?, com.schwarckstudio.lionfitness.core.model.SetType) -> Unit,
    onFinishWorkout: () -> Unit,
    onStartStandaloneWorkout: (com.schwarckstudio.lionfitness.core.model.Routine?) -> Unit,
    onAddExerciseClick: () -> Unit,
    onExerciseSelected: (com.schwarckstudio.lionfitness.core.model.Exercise) -> Unit,
    onCancelExercisePicker: () -> Unit,
    isPhoneWorkoutAvailable: Boolean,
    onSyncPhoneWorkout: () -> Unit,
    showFinishScreen: Boolean,
    onForceSync: () -> Unit,
    onUpdateWorkoutName: (String, String) -> Unit,
    onAddSet: (String) -> Unit,
    onDeleteSet: (String, Int) -> Unit,
    onReplaceExercise: (Int) -> Unit,
    onDeleteExercise: (Int) -> Unit
) {
    MaterialTheme {
        Scaffold(
            timeText = { TimeText() }
        ) {
            if (showFinishScreen) {
                FinishWorkoutScreen(
                    workout = currentWorkout,
                    onSaveClick = { newName ->
                        if (currentWorkout != null) {
                            onUpdateWorkoutName(currentWorkout.id, newName)
                        }
                        onFinishWorkout() // This will reset state and hide screen
                    },
                    onDiscardClick = {
                        onFinishWorkout() // Just reset
                    }
                )
            } else if (showExercisePicker) {
                ExercisePickerScreen(
                    exercises = exercises,
                    onExerciseClick = { exercise ->
                        onExerciseSelected(exercise)
                        onCancelExercisePicker()
                    },
                    onCancel = onCancelExercisePicker
                )
            } else if (isWorkoutActive && currentWorkout != null) {
                WearActiveWorkoutScreen(
                    workout = currentWorkout, 
                    timerSeconds = timerSeconds, 
                    isTimerRunning = isTimerRunning, 
                    restTimerSeconds = restTimerSeconds,
                    calories = calories,
                    heartRate = heartRate,
                    onSetClick = onSetClick,
                    onUpdateSet = onUpdateSet,
                    onFinishWorkout = onFinishWorkout,
                    onAddExerciseClick = onAddExerciseClick,
                    onAddSet = onAddSet,
                    onDeleteSet = onDeleteSet,
                    onReplaceExercise = onReplaceExercise,
                    onDeleteExercise = onDeleteExercise
                )
            } else {
                // Routine List
                RoutineListScreen(
                    routines = routines,
                    onRoutineClick = { routine -> onStartStandaloneWorkout(routine) },
                    onEmptyWorkoutClick = { onStartStandaloneWorkout(null) },
                    showContinueButton = isPhoneWorkoutAvailable,
                    onContinueClick = onSyncPhoneWorkout,
                    onForceSyncClick = onForceSync
                )
            }
        }
    }
}
