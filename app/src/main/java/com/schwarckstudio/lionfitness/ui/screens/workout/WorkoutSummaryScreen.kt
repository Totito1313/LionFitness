package com.schwarckstudio.lionfitness.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutSummaryScreen(
    viewModel: WorkoutViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onSave: () -> Unit
) {
    val workout by viewModel.finishedWorkout.collectAsState()
    
    // Local state for editing
    var name by remember(workout) { mutableStateOf(workout?.name ?: "") }
    var description by remember(workout) { mutableStateOf(workout?.description ?: "") }

    // Update ViewModel when local state changes
    LaunchedEffect(name, description) {
        viewModel.updateFinishedWorkout(name, description)
    }

    if (workout == null) {
        // Handle case where there is no finished workout
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DesignSystem.Colors.Background),
            contentAlignment = Alignment.Center
        ) {
            Text("No hay resumen disponible", color = DesignSystem.Colors.TextSecondary)
            Button(onClick = onNavigateBack) { Text("Volver") }
        }
        return
    }

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current

    LaunchedEffect(Unit) {
        topBarState.update(
            variant = TopBarVariant.FinishTraining,
            title = "Finalizar",
            isVisible = true,
            onBackClick = { viewModel.returnToWorkout(onNavigateBack) },
            onActionClick = { viewModel.saveFinishedWorkout(onSave) }
        )
    }

    Scaffold(
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            // Title Input
            Text("TÍTULO", style = MaterialTheme.typography.labelMedium, color = DesignSystem.Colors.TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = name,
                onValueChange = { name = it },
                textStyle = TextStyle(
                    color = DesignSystem.Colors.TextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                cursorBrush = SolidColor(DesignSystem.Colors.Primary),
                decorationBox = { innerTextField ->
                    if (name.isEmpty()) {
                        Text("Nombre del entrenamiento", color = DesignSystem.Colors.TextSecondary.copy(alpha = 0.5f), fontSize = 24.sp)
                    }
                    innerTextField()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Row
            Card(
                colors = CardDefaults.cardColors(containerColor = DesignSystem.Colors.Surface),
                shape = DesignSystem.Shapes.Card,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatItem("Duración", formatDuration(workout!!))
                    StatItem("Volumen", "${workout!!.totalVolume.toInt()} kg")
                    StatItem("Series", "${workout!!.totalSets}")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Date/Time
            Text("FECHA", style = MaterialTheme.typography.labelMedium, color = DesignSystem.Colors.TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formatDate(workout!!.startTime.toDate()),
                color = DesignSystem.Colors.Primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Photo/Video Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(DesignSystem.Shapes.Card)
                    .background(DesignSystem.Colors.Surface)
                    .clickable { /* TODO: Add photo logic */ },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, tint = DesignSystem.Colors.Primary, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Agregar foto / video", color = DesignSystem.Colors.TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Exercises List
            Text("EJERCICIOS", style = MaterialTheme.typography.labelMedium, color = DesignSystem.Colors.TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            
            workout!!.exercises.forEach { exercise ->
                SummaryExerciseCard(exercise = exercise)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Description Input
            Text("NOTAS", style = MaterialTheme.typography.labelMedium, color = DesignSystem.Colors.TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = DesignSystem.Colors.Surface),
                shape = DesignSystem.Shapes.Card,
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    textStyle = TextStyle(color = DesignSystem.Colors.TextPrimary, fontSize = 16.sp),
                    cursorBrush = SolidColor(DesignSystem.Colors.Primary),
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.padding(16.dp)) {
                            if (description.isEmpty()) {
                                Text(
                                    "¿Cómo ha ido tu entrenamiento?",
                                    color = DesignSystem.Colors.TextSecondary.copy(alpha = 0.5f),
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Discard Button
            TextButton(
                onClick = { viewModel.cancelWorkout(onNavigateBack) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Descartar Entrenamiento", color = MaterialTheme.colorScheme.error, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SummaryExerciseCard(exercise: com.schwarckstudio.lionfitness.core.model.WorkoutExercise) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DesignSystem.Colors.Surface),
        shape = DesignSystem.Shapes.Card,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = exercise.exerciseName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DesignSystem.Colors.TextPrimary
                )
                if (exercise.personalRecords.isNotEmpty()) {
                    Surface(
                        color = Color(0xFFFFD700).copy(alpha = 0.2f), // Gold background
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents, // Trophy icon
                                contentDescription = null,
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "PR",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFFFD700),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Sets Header
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("SET", modifier = Modifier.width(40.dp), style = MaterialTheme.typography.labelSmall, color = DesignSystem.Colors.TextSecondary)
                Text("KG", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelSmall, color = DesignSystem.Colors.TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                Text("REPS", modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelSmall, color = DesignSystem.Colors.TextSecondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            exercise.sets.filter { it.completed }.forEachIndexed { index, set ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Set Number with PR indicator
                    Box(modifier = Modifier.width(40.dp)) {
                        Text(
                            text = "${index + 1}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DesignSystem.Colors.TextSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    
                    // Weight
                    Text(
                        text = "${set.weight}",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = DesignSystem.Colors.TextPrimary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // Reps
                    Text(
                        text = "${set.reps}",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = DesignSystem.Colors.TextPrimary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = DesignSystem.Colors.TextSecondary, style = MaterialTheme.typography.labelSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(value, color = DesignSystem.Colors.TextPrimary, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}
