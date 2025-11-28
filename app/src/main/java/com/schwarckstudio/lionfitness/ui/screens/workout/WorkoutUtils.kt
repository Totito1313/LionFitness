package com.schwarckstudio.lionfitness.ui.screens.workout

import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDuration(workout: WorkoutLog): String {
    val durationSeconds = if (workout.endTime != null) {
        workout.endTime!!.seconds - workout.startTime.seconds
    } else {
        0
    }
    val minutes = durationSeconds / 60
    return "${minutes}min"
}

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
    return formatter.format(date)
}
