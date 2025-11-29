package com.schwarckstudio.lionfitness.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailScreen(
    viewModel: WorkoutDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val workout = uiState.workout

    var isEditing by remember { mutableStateOf(false) }
    var editedName by remember(workout) { mutableStateOf(workout?.name ?: "") }
    var editedDescription by remember(workout) { mutableStateOf(workout?.description ?: "") }

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    
    // Update TopBar state based on editing mode
    LaunchedEffect(isEditing, workout) {
        if (isEditing) {
            topBarState.update(
                variant = TopBarVariant.EditTraining,
                title = "Editar Entrenamiento",
                isVisible = true,
                onBackClick = { isEditing = false },
                onActionClick = {
                    if (workout != null) {
                        viewModel.updateWorkout(workout.copy(name = editedName, description = editedDescription))
                        isEditing = false
                    }
                }
            )
        } else {
            topBarState.update(
                variant = TopBarVariant.TrainingSummary,
                title = workout?.name?.ifEmpty { "Entrenamiento" } ?: "Detalle",
                subtitle = "Detalles",
                isVisible = true,
                onBackClick = onNavigateBack,
                onMenuClick = { 
                    // Show menu logic is handled by TopBar component via state.menuItems
                    // But here we need to set the menu items
                    topBarState.menuItems = listOf(
                        com.schwarckstudio.lionfitness.ui.components.TopBarMenuItem(
                            text = "Editar",
                            onClick = {
                                isEditing = true
                                editedName = workout?.name ?: ""
                                editedDescription = workout?.description ?: ""
                            }
                        )
                    )
                }
            )
        }
    }

    Scaffold(
        containerColor = Color(0xFFF1F1F3)
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (workout != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            if (isEditing) {
                                OutlinedTextField(
                                    value = editedName,
                                    onValueChange = { editedName = it },
                                    label = { Text("Nombre") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = editedDescription,
                                    onValueChange = { editedDescription = it },
                                    label = { Text("Descripción") },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            } else {
                                Text(
                                    text = workout.name.ifEmpty { "Entrenamiento sin nombre" },
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                if (workout.description.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = workout.description,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider(color = Color(0xFFF1F1F3))
                            Spacer(modifier = Modifier.height(16.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.Gray)
                                Spacer(modifier = Modifier.width(8.dp))
                                val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
                                Text(
                                    text = dateFormat.format(workout.startTime.toDate()),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Timer, contentDescription = null, tint = Color.Gray)
                                Spacer(modifier = Modifier.width(8.dp))
                                val endTime = workout.endTime
                                val durationSeconds = if (endTime != null) {
                                    (endTime.seconds - workout.startTime.seconds)
                                } else {
                                    0
                                }
                                val durationMinutes = durationSeconds / 60
                                Text(
                                    text = "$durationMinutes min",
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Ejercicios Realizados",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                items(workout.exercises) { exercise ->
                    WorkoutExerciseItem(exercise)
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Entrenamiento no encontrado")
            }
        }
    }
}

@Composable
fun WorkoutExerciseItem(exercise: com.schwarckstudio.lionfitness.core.model.WorkoutExercise) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = exercise.exerciseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            exercise.sets.forEachIndexed { index, set ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Set ${index + 1}",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${set.reps} reps",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${set.weight} kg",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
