package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineDetailScreen(
    viewModel: RoutineDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onStartWorkout: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val routine = uiState.routine

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    LaunchedEffect(routine) {
        topBarState.update(
            variant = com.schwarckstudio.lionfitness.ui.components.TopBarVariant.RoutineDetails,
            title = routine?.name ?: "Detalles de Rutina",
            subtitle = "Detalles",
            onBackClick = onNavigateBack,
            onMenuClick = { /* TODO: Navigate to Edit Routine */ }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DesignSystem.Colors.Surface)
    ) {
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = DesignSystem.Colors.Primary)
            }
        } else if (routine != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
            ) {
                item {
                    Text(
                        text = routine.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DesignSystem.Colors.TextPrimary
                    )
                    if (routine.notes.isNotEmpty()) {
                        Text(
                            text = routine.notes,
                            fontSize = 16.sp,
                            color = DesignSystem.Colors.TextSecondary
                        )
                    }
                }

                item {
                    Text(
                        text = "Ejercicios (${routine.exercises.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DesignSystem.Colors.TextPrimary
                    )
                }

                items(routine.exercises) { exercise ->
                    RoutineExerciseItem(exercise)
                }
            }
            
            ExtendedFloatingActionButton(
                onClick = { onStartWorkout(routine.id) },
                containerColor = DesignSystem.Colors.Primary,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Iniciar Rutina")
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Rutina no encontrada", color = DesignSystem.Colors.TextSecondary)
            }
        }
    }
}

@Composable
fun RoutineExerciseItem(exercise: com.schwarckstudio.lionfitness.core.model.RoutineExercise) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = DesignSystem.Shapes.Card,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = exercise.exerciseName.ifEmpty { "Ejercicio" },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DesignSystem.Colors.TextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            
            exercise.templateSets.forEachIndexed { index, set ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Text(
                        text = "Set ${index + 1}",
                        fontSize = 14.sp,
                        color = DesignSystem.Colors.TextSecondary
                    )
                    Text(
                        text = "${set.targetReps} Reps",
                        fontSize = 14.sp,
                        color = DesignSystem.Colors.TextSecondary
                    )
                    if (set.targetWeight.isNotEmpty()) {
                        Text(
                            text = set.targetWeight,
                            fontSize = 14.sp,
                            color = DesignSystem.Colors.TextSecondary
                        )
                    }
                }
            }
        }
    }
}
