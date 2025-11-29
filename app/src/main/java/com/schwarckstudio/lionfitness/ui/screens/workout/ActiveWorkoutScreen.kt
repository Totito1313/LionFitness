package com.schwarckstudio.lionfitness.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveWorkoutScreen(
    viewModel: WorkoutViewModel = hiltViewModel(),
    onFinish: () -> Unit,
    onReplaceExercise: (String) -> Unit,
    onAddExercise: () -> Unit
) {
    val uiState by viewModel.workoutUiState.collectAsState()
    val timerRemaining by viewModel.timerRemaining.collectAsState()
    val isTimerRunning by viewModel.isTimerRunning.collectAsState()
    val heartRate by viewModel.heartRate.collectAsState(initial = 0)
    val calories by viewModel.caloriesBurned.collectAsState()
    val isWatchConnected by viewModel.isWatchConnected.collectAsState()
    
    var showPlateCalculator by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    
    val snackbarHostState = remember { SnackbarHostState() }
    
    LaunchedEffect(Unit) {
        viewModel.prEvents.collect { event ->
            snackbarHostState.showSnackbar(
                message = "¡Nuevo Récord! ${event.exerciseName}: ${event.value}",
                duration = SnackbarDuration.Short
            )
        }
    }
    
    val finishedWorkout by viewModel.finishedWorkout.collectAsState()
    
    LaunchedEffect(finishedWorkout) {
        if (finishedWorkout != null) {
            onFinish()
        }
    }

    val menuItems = listOf(
        com.schwarckstudio.lionfitness.ui.components.TopBarMenuItem("Calculadora de Platos", { showPlateCalculator = true }),
        com.schwarckstudio.lionfitness.ui.components.TopBarMenuItem("Cancelar Entrenamiento", { viewModel.cancelWorkout(onFinish) }, Color.Red)
    )

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    LaunchedEffect(timerRemaining, heartRate, menuItems) {
        topBarState.update(
            variant = TopBarVariant.ActiveTraining,
            duration = formatTime(timerRemaining),
            heartRate = heartRate.toString(),
            onActionClick = { viewModel.finishWorkout(onFinish) },
            onBackClick = { /* Handled by TopBar */ },
            menuItems = menuItems
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DesignSystem.Colors.Background)
    ) {
        if (showPlateCalculator) {
            PlateCalculatorDialog(onDismiss = { showPlateCalculator = false })
        }
        
        when (val state = uiState) {
            is WorkoutUiState.Inactive -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Cargando entrenamiento...", color = Color.Gray)
                }
            }
            is WorkoutUiState.Active -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
                ) {
                    items(state.exercises) { exercise ->
                        ExerciseCard(
                            exercise = exercise,
                            onAddSet = { viewModel.addSet(exercise.id) },
                            onUpdateSet = { index, weight, reps, rpe ->
                                viewModel.updateSet(exercise.id, index, weight, reps, rpe)
                            },
                            onToggleSetComplete = { index, isCompleted ->
                                viewModel.toggleSetComplete(exercise.id, index, isCompleted)
                            },
                            onReplaceExercise = { onReplaceExercise(exercise.id) }
                        )
                    }
                    
                    item {
                        Button(
                            onClick = onAddExercise,
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = DesignSystem.Colors.Primary
                            ),
                            shape = DesignSystem.Shapes.Button,
                            elevation = ButtonDefaults.buttonElevation(2.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Agregar Ejercicio", fontWeight = FontWeight.Bold)
                        }
                    }

                }
            }
        }

        if (isTimerRunning) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .padding(bottom = 80.dp)
            ) {
                RestTimer(
                    remainingSeconds = timerRemaining,
                    onStop = viewModel::stopTimer,
                    onAdd = { viewModel.addTime(10) },
                    onSubtract = { viewModel.subtractTime(10) }
                )
            }
        }
        
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun StatItem(icon: ImageVector, value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = DesignSystem.Colors.TextPrimary)
        }
        Text(text = label, fontSize = 10.sp, color = Color.Gray)
    }
}

@Composable
fun ExerciseCard(
    exercise: ExerciseUiData,
    onAddSet: () -> Unit,
    onUpdateSet: (Int, String, String, String) -> Unit,
    onToggleSetComplete: (Int, Boolean) -> Unit,
    onReplaceExercise: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = DesignSystem.Shapes.Card,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DesignSystem.Colors.TextPrimary,
                    modifier = Modifier.weight(1f)
                )
                Box {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = Color.Gray)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        containerColor = Color.White
                    ) {
                        DropdownMenuItem(
                            text = { Text("Reemplazar Ejercicio") },
                            onClick = {
                                showMenu = false
                                onReplaceExercise()
                            }
                        )
                    }
                }
            }
            
            // Table Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("SET", modifier = Modifier.width(30.dp), color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("PREVIOUS", modifier = Modifier.weight(1f), color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("KG", modifier = Modifier.width(60.dp), color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("REPS", modifier = Modifier.width(60.dp), color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("RPE", modifier = Modifier.width(40.dp), color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Text("✓", modifier = Modifier.width(40.dp), color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }

            // Sets
            exercise.sets.forEach { setUiData ->
                SetRow(
                    setUiData = setUiData,
                    onUpdateSet = onUpdateSet,
                    onToggleSetComplete = onToggleSetComplete
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Button(
                onClick = onAddSet,
                modifier = Modifier.fillMaxWidth().height(36.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F9FA), // Light gray
                    contentColor = DesignSystem.Colors.Primary
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("AÑADIR SET", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SetRow(
    setUiData: SetUiData,
    onUpdateSet: (Int, String, String, String) -> Unit,
    onToggleSetComplete: (Int, Boolean) -> Unit
) {
    val set = setUiData.current
    val previous = setUiData.previous
    val backgroundColor = if (set.completed) Color(0xFFE8F5E9) else Color.Transparent // Light Green
    
    val previousText = if (previous != null) {
        "${previous.weight}kg x ${previous.reps}"
    } else {
        "-"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Set Number
        Text(
            "${setUiData.index + 1}",
            modifier = Modifier.width(30.dp),
            fontWeight = FontWeight.SemiBold,
            color = if (set.completed) Color(0xFF4CAF50) else Color.Gray,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        
        // Previous
        Text(
            previousText,
            modifier = Modifier.weight(1f),
            color = Color.LightGray,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        
        // Weight
        CompactTextField(
            value = if (set.weight > 0) set.weight.toString() else "",
            onValueChange = { onUpdateSet(setUiData.index, it, set.reps.toString(), set.rpe?.toString() ?: "") },
            modifier = Modifier.width(60.dp),
            completed = set.completed
        )
        
        // Reps
        CompactTextField(
            value = if (set.reps > 0) set.reps.toString() else "",
            onValueChange = { onUpdateSet(setUiData.index, set.weight.toString(), it, set.rpe?.toString() ?: "") },
            modifier = Modifier.width(60.dp),
            completed = set.completed
        )
        
        // RPE
        CompactTextField(
            value = set.rpe?.toString() ?: "",
            onValueChange = { onUpdateSet(setUiData.index, set.weight.toString(), set.reps.toString(), it) },
            modifier = Modifier.width(40.dp),
            completed = set.completed,
            placeholder = "-"
        )
        
        // Checkbox
        Checkbox(
            checked = set.completed,
            onCheckedChange = { onToggleSetComplete(setUiData.index, it) },
            modifier = Modifier.width(40.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = DesignSystem.Colors.Primary,
                uncheckedColor = Color.LightGray
            )
        )
    }
}

@Composable
fun CompactTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    completed: Boolean = false,
    placeholder: String = ""
) {
    val backgroundColor = if (completed) Color(0x80FFFFFF) else Color(0xFFF1F3F5) // Light gray background
    
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(30.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(horizontal = 4.dp, vertical = 6.dp),
        textStyle = TextStyle(
            color = if (completed) Color.Gray else DesignSystem.Colors.TextPrimary,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(contentAlignment = Alignment.Center) {
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    Text(placeholder, color = Color.LightGray, fontSize = 14.sp)
                }
                innerTextField()
            }
        }
    )
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}

@Composable
fun RestTimer(
    remainingSeconds: Int,
    onStop: () -> Unit,
    onAdd: () -> Unit,
    onSubtract: () -> Unit
) {
    Card(
        modifier = Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF212529) // Dark background for timer
        ),
        shape = DesignSystem.Shapes.Card,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = formatTime(remainingSeconds),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Row {
                FilledIconButton(
                    onClick = onSubtract,
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF343a40))
                ) { Text("-10", color = Color.White, fontSize = 12.sp) }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                FilledIconButton(
                    onClick = onAdd,
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFF343a40))
                ) { Text("+10", color = Color.White, fontSize = 12.sp) }
            }
            
            IconButton(onClick = onStop) {
                Icon(Icons.Default.Close, contentDescription = "Stop Timer", tint = Color.White)
            }
        }
    }
}
