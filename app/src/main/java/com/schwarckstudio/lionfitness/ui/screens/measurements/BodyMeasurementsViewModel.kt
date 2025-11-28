package com.schwarckstudio.lionfitness.ui.screens.measurements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BodyMeasurement(
    val id: Int,
    val name: String,
    val value: String,
    val unit: String,
    val change: Double,
    val colorStart: Long,
    val colorEnd: Long,
    val isPositive: Boolean
)

data class WeightDataPoint(
    val date: String,
    val weight: Float
)

data class MeasurementHistoryItem(
    val date: String,
    val summary: String
)

data class BodyMeasurementsUiState(
    val measurements: List<BodyMeasurement> = emptyList(),
    val weightHistory: List<WeightDataPoint> = emptyList(),
    val history: List<MeasurementHistoryItem> = emptyList(),
    val lastUpdate: String = "hace 3 días"
)

@HiltViewModel
class BodyMeasurementsViewModel @Inject constructor(
    private val repository: com.schwarckstudio.lionfitness.core.data.repository.BodyMeasurementsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BodyMeasurementsUiState())
    val uiState: StateFlow<BodyMeasurementsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getMeasurementLogsFlow().collect { logs ->
                updateUiState(logs)
            }
        }
    }

    private fun updateUiState(logs: List<com.schwarckstudio.lionfitness.core.model.BodyMeasurementLog>) {
        val latestLog = logs.firstOrNull()
        val previousLog = logs.getOrNull(1)

        val measurements = listOf(
            createBodyMeasurement(1, "Peso", latestLog?.weight, previousLog?.weight, "kg", 0xFF4c6ef5, 0xFF5f3dc4, false),
            createBodyMeasurement(2, "Pecho", latestLog?.chest, previousLog?.chest, "cm", 0xFFff6b6b, 0xFFee5a6f, true),
            createBodyMeasurement(3, "Cintura", latestLog?.waist, previousLog?.waist, "cm", 0xFF20c997, 0xFF12b886, false),
            createBodyMeasurement(4, "Brazos", latestLog?.arms, previousLog?.arms, "cm", 0xFFffd43b, 0xFFfab005, true),
            createBodyMeasurement(5, "Piernas", latestLog?.legs, previousLog?.legs, "cm", 0xFF845ef7, 0xFF7048e8, true),
            createBodyMeasurement(6, "Caderas", latestLog?.hips, previousLog?.hips, "cm", 0xFFff8787, 0xFFff6b6b, false)
        )

        val weightHistory = logs.take(6).mapNotNull { log ->
            val weight = log.weight
            if (weight != null) {
                val dateFormat = java.text.SimpleDateFormat("MMM", java.util.Locale.getDefault())
                WeightDataPoint(dateFormat.format(log.date.toDate()), weight.toFloat())
            } else null
        }.reversed()

        val history = logs.map { log ->
            val dateFormat = java.text.SimpleDateFormat("dd 'de' MMMM, yyyy", java.util.Locale.getDefault())
            val count = listOfNotNull(log.weight, log.chest, log.waist, log.arms, log.legs, log.hips).size
            MeasurementHistoryItem(dateFormat.format(log.date.toDate()), "$count medidas registradas")
        }

        _uiState.value = BodyMeasurementsUiState(
            measurements = measurements,
            weightHistory = weightHistory,
            history = history,
            lastUpdate = if (latestLog != null) "Actualizado hoy" else "Sin datos"
        )
    }

    private fun createBodyMeasurement(
        id: Int,
        name: String,
        current: Double?,
        previous: Double?,
        unit: String,
        colorStart: Long,
        colorEnd: Long,
        isPositiveGood: Boolean
    ): BodyMeasurement {
        val currentValue = current ?: 0.0
        val previousValue = previous ?: 0.0
        val change = if (previous != null) currentValue - previousValue else 0.0
        
        return BodyMeasurement(
            id = id,
            name = name,
            value = if (current != null) String.format("%.1f", currentValue) else "-",
            unit = unit,
            change = change,
            colorStart = colorStart,
            colorEnd = colorEnd,
            isPositive = if (isPositiveGood) change >= 0 else change <= 0
        )
    }
    
    fun addMeasurement(
        weight: Double?,
        chest: Double?,
        waist: Double?,
        arms: Double?,
        legs: Double?,
        hips: Double?
    ) {
        viewModelScope.launch {
            val newLog = com.schwarckstudio.lionfitness.core.model.BodyMeasurementLog(
                weight = weight,
                chest = chest,
                waist = waist,
                arms = arms,
                legs = legs,
                hips = hips,
                date = com.google.firebase.Timestamp.now()
            )
            repository.saveMeasurementLog(newLog)
        }
    }
}
