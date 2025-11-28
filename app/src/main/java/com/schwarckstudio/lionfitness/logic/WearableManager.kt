package com.schwarckstudio.lionfitness.logic

import android.content.Context
import android.util.Log
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.dto.WorkoutDTO
import com.schwarckstudio.lionfitness.core.model.dto.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.google.android.gms.wearable.Node
import com.schwarckstudio.lionfitness.core.model.dto.toDTO

sealed class WearableEvent {
    data class ToggleSet(val exerciseId: String, val setIndex: Int, val isCompleted: Boolean) : WearableEvent()
    data class UpdateSet(val exerciseId: String, val setIndex: Int, val weight: Double, val reps: Int, val rpe: Int?) : WearableEvent()
    object FinishWorkout : WearableEvent()
    object RequestState : WearableEvent()
    data class FinishedWorkoutReceived(val workout: WorkoutLog) : WearableEvent()
    data class ReplaceExercise(val oldExerciseId: String, val newExerciseId: String) : WearableEvent()
}



@Singleton
class WearableManager @Inject constructor(
    @ApplicationContext private val context: Context
) : MessageClient.OnMessageReceivedListener, CapabilityClient.OnCapabilityChangedListener, DataClient.OnDataChangedListener {

    private val messageClient: MessageClient = Wearable.getMessageClient(context)
    private val capabilityClient: CapabilityClient = Wearable.getCapabilityClient(context)
    private val dataClient: com.google.android.gms.wearable.DataClient = Wearable.getDataClient(context)
    private val gson = com.google.gson.Gson()

    private val _heartRate = MutableStateFlow<Int>(0)
    val heartRate: StateFlow<Int> = _heartRate.asStateFlow()

    private val _events = MutableSharedFlow<WearableEvent>()
    val events: SharedFlow<WearableEvent> = _events.asSharedFlow()

    private val _connectedNodes = MutableStateFlow<List<Node>>(emptyList())
    val connectedNodes: StateFlow<List<Node>> = _connectedNodes.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    companion object {
        private const val WEAR_CAPABILITY = "wear_app_capability"
        private const val START_WORKOUT_PATH = "/start_workout"
        private const val STOP_WORKOUT_PATH = "/stop_workout"
        private const val HEART_RATE_PATH = "/heart_rate"
        private const val WORKOUT_DATA_PATH = "/active_workout"
        private const val TOGGLE_SET_PATH = "/toggle_set"
        private const val UPDATE_SET_PATH = "/update_set"
        private const val FINISH_WORKOUT_PATH = "/finish_workout_request"
        private const val TIMER_STATE_PATH = "/timer_state"
        private const val REQUEST_STATE_PATH = "/request_state"
    private const val REPLACE_EXERCISE_PATH = "/replace_exercise"
    }

    init {
        messageClient.addListener(this)
        capabilityClient.addListener(this, WEAR_CAPABILITY)
        dataClient.addListener(this)
        scope.launch {
            updateConnectedNodes()
        }
    }

    private suspend fun updateConnectedNodes() {
        try {
            val capabilityInfo = capabilityClient
                .getCapability(WEAR_CAPABILITY, CapabilityClient.FILTER_REACHABLE)
                .await()
            _connectedNodes.value = capabilityInfo.nodes.toList()
        } catch (e: Exception) {
            Log.e("WearableManager", "Failed to get connected nodes", e)
        }
    }

    override fun onCapabilityChanged(capabilityInfo: com.google.android.gms.wearable.CapabilityInfo) {
        _connectedNodes.value = capabilityInfo.nodes.toList()
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        when (messageEvent.path) {
            HEART_RATE_PATH -> {
                val hr = messageEvent.data.toString(Charsets.UTF_8).toIntOrNull() ?: 0
                _heartRate.value = hr
            }
            TOGGLE_SET_PATH -> {
                val parts = String(messageEvent.data).split("|")
                if (parts.size == 3) {
                    val exerciseId = parts[0]
                    val setIndex = parts[1].toIntOrNull() ?: return
                    val isCompleted = parts[2].toBoolean()
                    // Emit event. Since onMessageReceived is called on a binder thread, we need a scope.
                    // But SharedFlow.emit is suspend. tryEmit is non-suspend but might fail if buffer full.
                    // We can use runBlocking or a scope. runBlocking is safe here as it's a background thread.
                    // Emit event using the class scope to avoid blocking the binder thread
                    scope.launch {
                        _events.emit(WearableEvent.ToggleSet(exerciseId, setIndex, isCompleted))
                    }
                }
            }
            UPDATE_SET_PATH -> {
                val parts = String(messageEvent.data).split("|")
                if (parts.size >= 4) {
                    val exerciseId = parts[0]
                    val setIndex = parts[1].toIntOrNull() ?: return
                    val weight = parts[2].toDoubleOrNull() ?: 0.0
                    val reps = parts[3].toIntOrNull() ?: 0
                    val rpe = parts.getOrNull(4)?.toIntOrNull()
                    
                    scope.launch {
                        _events.emit(WearableEvent.UpdateSet(exerciseId, setIndex, weight, reps, rpe))
                    }
                }
            }
            FINISH_WORKOUT_PATH -> {
                scope.launch {
                    _events.emit(WearableEvent.FinishWorkout)
                }
            }
            REQUEST_STATE_PATH -> {
                scope.launch {
                    _events.emit(WearableEvent.RequestState)
                }
            }
            REPLACE_EXERCISE_PATH -> {
                val parts = String(messageEvent.data).split("|")
                if (parts.size >= 2) {
                    val oldExerciseId = parts[0] // Or index? Watch sends index, but we need ID or index.
                    // Wait, Watch sends: "$exerciseIndex|$newExerciseId" ?
                    // Let's check WearViewModel.
                    // WearViewModel calls: messageClient.sendMessage(node.id, "/replace_exercise", "$exerciseIndex|$newExerciseId".toByteArray())
                    // But wait, WearViewModel currently DOES NOT send this message. I need to implement it there too.
                    // Let's assume it sends: "$oldExerciseId|$newExerciseId" or "$index|$newExerciseId"
                    // The user said: "cuando se remplaza un ejercicio en el reloj no se cambia en el telefono"
                    // So I need to implement the SENDing on watch and RECEIVING on phone.
                    
                    // Let's assume we will send: "oldExerciseId|newExerciseId" from Watch.
                    // But Watch uses Index for local replacement.
                    // It should probably map Index -> ExerciseId before sending.
                    
                    val newExerciseId = parts[1]
                    scope.launch {
                        _events.emit(WearableEvent.ReplaceExercise(oldExerciseId, newExerciseId))
                    }
                }
            }
        }
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        dataEvents.forEach { event ->
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == "/finished_workout") {
                val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                val json = dataMap.getString("workout_data")
                if (json != null) {
                    try {
                        val dto = gson.fromJson(json, WorkoutDTO::class.java)
                        val workout = dto.toDomain()
                        scope.launch {
                            _events.emit(WearableEvent.FinishedWorkoutReceived(workout))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    suspend fun sendStartWorkoutMessage(workoutId: String) {
        try {
            val nodes = capabilityClient
                .getCapability(WEAR_CAPABILITY, CapabilityClient.FILTER_REACHABLE)
                .await()
                .nodes

            nodes.forEach { node ->
                messageClient.sendMessage(node.id, START_WORKOUT_PATH, workoutId.toByteArray()).await()
                Log.d("WearableManager", "Sent start workout message to ${node.displayName}")
            }
        } catch (e: Exception) {
            Log.e("WearableManager", "Failed to send start workout message", e)
        }
    }

    suspend fun sendStopWorkoutMessage() {
        try {
            val nodes = capabilityClient
                .getCapability(WEAR_CAPABILITY, CapabilityClient.FILTER_REACHABLE)
                .await()
                .nodes

            nodes.forEach { node ->
                messageClient.sendMessage(node.id, STOP_WORKOUT_PATH, byteArrayOf()).await()
                Log.d("WearableManager", "Sent stop workout message to ${node.displayName}")
            }
        } catch (e: Exception) {
            Log.e("WearableManager", "Failed to send stop workout message", e)
        }
    }



    suspend fun sendWorkoutState(workout: com.schwarckstudio.lionfitness.core.model.WorkoutLog) {
        try {
            val dto = workout.toDTO()
            val json = gson.toJson(dto)
            val putDataMapRequest = com.google.android.gms.wearable.PutDataMapRequest.create(WORKOUT_DATA_PATH)
            putDataMapRequest.dataMap.putString("workout_data", json)
            putDataMapRequest.dataMap.putLong("timestamp", System.currentTimeMillis())
            val request = putDataMapRequest.asPutDataRequest()
            request.setUrgent()
            dataClient.putDataItem(request).await()
            Log.d("WearableManager", "Sent workout state: ${workout.id}")
        } catch (e: Exception) {
            Log.e("WearableManager", "Failed to send workout state", e)
        }
    }

    suspend fun sendTimerState(remainingSeconds: Int, isRunning: Boolean, calories: Int) {
        try {
            val nodes = capabilityClient
                .getCapability(WEAR_CAPABILITY, CapabilityClient.FILTER_REACHABLE)
                .await()
                .nodes

            val data = "$remainingSeconds|$isRunning|$calories".toByteArray(Charsets.UTF_8)

            nodes.forEach { node ->
                messageClient.sendMessage(node.id, TIMER_STATE_PATH, data).await()
            }
        } catch (e: Exception) {
            Log.e("WearableManager", "Failed to send timer state", e)
        }
    }
}
