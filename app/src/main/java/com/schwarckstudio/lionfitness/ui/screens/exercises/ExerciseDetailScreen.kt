package com.schwarckstudio.lionfitness.ui.screens.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exerciseId: String,
    viewModel: ExerciseViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val exercise = viewModel.getExercise(exerciseId)

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    LaunchedEffect(exercise) {
        topBarState.update(
            variant = com.schwarckstudio.lionfitness.ui.components.TopBarVariant.TrainingSummary,
            title = exercise?.name ?: "Detalle del Ejercicio",
            subtitle = "Detalle",
            onBackClick = onNavigateBack
        )
    }

    Scaffold(
        // TopBar handled by LionFitnessApp
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            if (exercise != null) {
                Text(text = "Primary Muscle: ${exercise.primaryMuscle}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Type: ${exercise.type}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "History", style = MaterialTheme.typography.titleLarge)
                Text(text = "Coming soon in Phase 5...", style = MaterialTheme.typography.bodyMedium)
            } else {
                Text("Exercise not found.")
            }
        }
    }
}
