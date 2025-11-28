package com.schwarckstudio.lionfitness.core.data.repository

import com.google.firebase.Timestamp
import com.schwarckstudio.lionfitness.core.data.csv.CsvManager
import com.schwarckstudio.lionfitness.core.model.BodyMeasurementLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class CsvBodyMeasurementsRepository @Inject constructor(
    private val csvManager: CsvManager
) : BodyMeasurementsRepository {

    private val _measurementsFlow = MutableStateFlow<List<BodyMeasurementLog>>(emptyList())
    private val fileName = "measurements.csv"

    init {
        loadMeasurements()
    }

    private fun loadMeasurements() {
        val data = csvManager.readCsv(fileName)
        val logs = data.map { row ->
            BodyMeasurementLog(
                id = row["id"] ?: "",
                date = try {
                    val time = row["date"]?.toLongOrNull() ?: System.currentTimeMillis()
                    Timestamp(Date(time))
                } catch (e: Exception) { Timestamp.now() },
                weight = row["weight"]?.toDoubleOrNull(),
                height = row["height"]?.toDoubleOrNull(),
                chest = row["chest"]?.toDoubleOrNull(),
                waist = row["waist"]?.toDoubleOrNull(),
                arms = row["arms"]?.toDoubleOrNull(),
                legs = row["legs"]?.toDoubleOrNull(),
                hips = row["hips"]?.toDoubleOrNull()
            )
        }.sortedByDescending { it.date }
        _measurementsFlow.value = logs
    }

    override suspend fun getMeasurementLogs(): List<BodyMeasurementLog> {
        return _measurementsFlow.value
    }

    override fun getMeasurementLogsFlow(): Flow<List<BodyMeasurementLog>> {
        return _measurementsFlow.asStateFlow()
    }

    override suspend fun saveMeasurementLog(log: BodyMeasurementLog) {
        val currentLogs = _measurementsFlow.value.toMutableList()
        val logToSave = if (log.id.isEmpty()) log.copy(id = UUID.randomUUID().toString()) else log
        
        val index = currentLogs.indexOfFirst { it.id == logToSave.id }
        if (index != -1) {
            currentLogs[index] = logToSave
        } else {
            currentLogs.add(logToSave)
        }

        val sortedLogs = currentLogs.sortedByDescending { it.date }
        _measurementsFlow.value = sortedLogs
        saveToCsv(sortedLogs)
    }

    private fun saveToCsv(logs: List<BodyMeasurementLog>) {
        val data = logs.map { log ->
            mapOf(
                "id" to log.id,
                "date" to log.date.toDate().time.toString(),
                "weight" to (log.weight?.toString() ?: ""),
                "height" to (log.height?.toString() ?: ""),
                "chest" to (log.chest?.toString() ?: ""),
                "waist" to (log.waist?.toString() ?: ""),
                "arms" to (log.arms?.toString() ?: ""),
                "legs" to (log.legs?.toString() ?: ""),
                "hips" to (log.hips?.toString() ?: "")
            )
        }
        csvManager.writeCsv(fileName, data)
    }

    override suspend fun getLatestBodyMeasurement(): BodyMeasurementLog? {
        return _measurementsFlow.value.firstOrNull()
    }
}
