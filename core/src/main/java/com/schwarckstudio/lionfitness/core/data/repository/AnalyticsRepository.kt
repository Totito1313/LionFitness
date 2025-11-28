package com.schwarckstudio.lionfitness.core.data.repository

import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import javax.inject.Inject

data class WeeklyStats(
    val totalWorkouts: Int,
    val totalDurationMinutes: Long,
    val totalCaloriesBurned: Int, // Estimated
    val totalVolume: Double,
    val workoutsPerDay: Map<LocalDate, Int>
)

data class UserStats(
    val streakDays: Int,
    val totalWorkouts: Int,
    val totalDurationHours: Double,
    val weeklyStats: WeeklyStats
)

data class GlobalRecords(
    val maxVolume: Double = 0.0,
    val maxDurationSeconds: Long = 0,
    val maxCalories: Double = 0.0,
    val maxDistance: Double = 0.0,
    val maxWeight: Double = 0.0,
    val maxReps: Int = 0
)

interface AnalyticsRepository {
    fun getUserStats(): Flow<UserStats>
    fun getGlobalRecords(): Flow<GlobalRecords>
}

class AnalyticsRepositoryImpl @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : AnalyticsRepository {

    override fun getUserStats(): Flow<UserStats> {
        return workoutRepository.getWorkoutsFlow().map { workouts ->
            calculateUserStats(workouts)
        }
    }

    override fun getGlobalRecords(): Flow<GlobalRecords> {
        return workoutRepository.getWorkoutsFlow().map { workouts ->
            calculateGlobalRecords(workouts)
        }
    }

    private fun calculateGlobalRecords(workouts: List<WorkoutLog>): GlobalRecords {
        if (workouts.isEmpty()) return GlobalRecords()

        val maxVolume = workouts.maxByOrNull { it.totalVolume }
        val maxDuration = workouts.maxByOrNull { 
            (it.endTime?.toDate()?.time ?: it.startTime.toDate().time) - it.startTime.toDate().time 
        }
        val maxCalories = workouts.maxByOrNull { it.calories }
        val maxDistance = workouts.maxByOrNull { it.distance }

        // Max Weight and Reps across all sets in all workouts
        var maxWeight = 0.0
        var maxReps = 0
        
        workouts.forEach { workout ->
            workout.exercises.forEach { exercise ->
                exercise.sets.forEach { set ->
                    if (set.weight > maxWeight) maxWeight = set.weight
                    if (set.reps > maxReps) maxReps = set.reps
                }
            }
        }

        return GlobalRecords(
            maxVolume = maxVolume?.totalVolume ?: 0.0,
            maxDurationSeconds = maxDuration?.let { 
                ((it.endTime?.toDate()?.time ?: it.startTime.toDate().time) - it.startTime.toDate().time) / 1000 
            } ?: 0,
            maxCalories = maxCalories?.calories ?: 0.0,
            maxDistance = maxDistance?.distance ?: 0.0,
            maxWeight = maxWeight,
            maxReps = maxReps
        )
    }

    private fun calculateUserStats(workouts: List<WorkoutLog>): UserStats {
        val now = LocalDate.now()
        val sortedWorkouts = workouts.sortedByDescending { it.startTime.toDate().time }
        
        // Calculate Streak
        var currentStreak = 0
        if (sortedWorkouts.isNotEmpty()) {
            var lastDate = sortedWorkouts.first().startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            if (lastDate == now || lastDate == now.minusDays(1)) {
                currentStreak = 1
                var checkDate = lastDate.minusDays(1)
                
                // Simple streak calculation (consecutive days)
                // This is a simplified version, robust streak logic can be complex
                val uniqueDates = sortedWorkouts.map { 
                    it.startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() 
                }.distinct()
                
                for (i in 1 until uniqueDates.size) {
                    if (uniqueDates[i] == uniqueDates[i-1].minusDays(1)) {
                        currentStreak++
                    } else {
                        break
                    }
                }
            }
        }

        val totalDurationMillis = workouts.sumOf { (it.endTime?.toDate()?.time ?: it.startTime.toDate().time) - it.startTime.toDate().time }
        val totalDurationHours = totalDurationMillis / (1000.0 * 60 * 60)
        
        // Weekly Stats (Last 7 days)
        val oneWeekAgo = now.minusDays(7)
        val weeklyWorkouts = workouts.filter { 
            val date = it.startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            date.isAfter(oneWeekAgo) || date.isEqual(oneWeekAgo)
        }
        
    val weeklyDurationMillis = weeklyWorkouts.sumOf { (it.endTime?.toDate()?.time ?: it.startTime.toDate().time) - it.startTime.toDate().time }
        val weeklyVolume = weeklyWorkouts.sumOf { it.totalVolume }
        
        // Estimate calories: ~5 kcal/min for weightlifting (very rough estimate)
        val weeklyCalories = (weeklyDurationMillis / (1000 * 60)) * 5 
        
        val workoutsPerDay = weeklyWorkouts.groupBy { 
            it.startTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        }.mapValues { it.value.size }

        return UserStats(
            streakDays = currentStreak,
            totalWorkouts = workouts.size,
            totalDurationHours = totalDurationHours,
            weeklyStats = WeeklyStats(
                totalWorkouts = weeklyWorkouts.size,
                totalDurationMinutes = weeklyDurationMillis / (1000 * 60),
                totalCaloriesBurned = weeklyCalories.toInt(),
                totalVolume = weeklyVolume,
                workoutsPerDay = workoutsPerDay
            )
        )
    }
}
