package com.schwarckstudio.lionfitness.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Exercises : Screen("exercises", "Exercises", Icons.Default.FitnessCenter)
    object Routines : Screen("routines", "Routines", Icons.Default.List)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    
    // Detail screens
    object ExerciseDetail : Screen("exercise/{exerciseId}", "Exercise Detail") {
        fun createRoute(exerciseId: String) = "exercise/$exerciseId"
    }
    object CreateRoutine : Screen("create_routine", "Create Routine")
    object AddExercise : Screen("add_exercise", "Add Exercise")
    object CreateCustomExercise : Screen("create_custom_exercise", "Create Custom Exercise")
    object ActiveWorkout : Screen("active_workout?routineId={routineId}", "Active Workout") {
        fun createRoute(routineId: String? = null) = if (routineId != null) "active_workout?routineId=$routineId" else "active_workout"
    }
    object WorkoutSummary : Screen("workout_summary", "Workout Summary")
    object ExercisePicker : Screen("exercise_picker", "Select Exercise")
    object GetStarted : Screen("get_started", "Get Started")
    object Statistics : Screen("statistics", "Statistics")
    object MyRoutines : Screen("my_routines", "My Routines")
    object BodyMeasurements : Screen("body_measurements", "Body Measurements")
    
    object RoutineDetail : Screen("routine/{routineId}", "Routine Detail") {
        fun createRoute(routineId: String) = "routine/$routineId"
    }
    
    object WorkoutDetail : Screen("workout/{workoutId}", "Workout Detail") {
        fun createRoute(workoutId: String) = "workout/$workoutId"
    }
    object EditProfile : Screen("edit_profile", "Edit Profile")
    object Notifications : Screen("notifications", "Notifications")
    object PrivacySecurity : Screen("privacy_security", "Privacy & Security")
}
