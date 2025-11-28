package com.schwarckstudio.lionfitness.wear.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.itemsIndexed
import androidx.compose.ui.window.Dialog
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.core.model.WorkoutSet
import com.schwarckstudio.lionfitness.core.model.SetType
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WearActiveWorkoutScreen(
    workout: WorkoutLog,
    timerSeconds: Int,
    isTimerRunning: Boolean,
    restTimerSeconds: Int,
    calories: Int,
    heartRate: Int,
    onSetClick: (exerciseId: String, setIndex: Int, isCompleted: Boolean) -> Unit,
    onUpdateSet: (exerciseId: String, setIndex: Int, weight: Double, reps: Int, rpe: Int?, type: com.schwarckstudio.lionfitness.core.model.SetType) -> Unit,
    onFinishWorkout: () -> Unit,
    onAddExerciseClick: () -> Unit,
    onAddSet: (exerciseId: String) -> Unit,
    onDeleteSet: (exerciseId: String, setIndex: Int) -> Unit,
    onReplaceExercise: (exerciseIndex: Int) -> Unit,
    onDeleteExercise: (exerciseIndex: Int) -> Unit
) {
    val pagerState = rememberPagerState()
    var editingSet by remember { mutableStateOf<Triple<String, Int, WorkoutSet>?>(null) }
    var showExerciseOptions by remember { mutableStateOf(false) }
    
    // Dialog for editing sets
    if (editingSet != null) {
        val (exerciseId, index, set) = editingSet!!
        SetEditorDialog(
            showDialog = true,
            initialWeight = editingSet!!.third.weight,
            initialReps = editingSet!!.third.reps,
            initialRpe = editingSet!!.third.rpe?.toInt(),
            initialType = editingSet!!.third.type,
            onDismiss = { editingSet = null },
            onConfirm = { weight, reps, rpe, type ->
                onUpdateSet(editingSet!!.first, editingSet!!.second, weight, reps, rpe, type)
                editingSet = null
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        if (workout.exercises.isNotEmpty()) {
            HorizontalPager(
                count = workout.exercises.size + 1, // +1 for the "Add Exercise" / "Finish" page
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                if (page < workout.exercises.size) {
                    val currentExercise = workout.exercises[page]
                    
                    ScalingLazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 30.dp, bottom = 40.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Header: Exercise Name & Notes
                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = currentExercise.exerciseName,
                                    style = MaterialTheme.typography.title3,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                                )
                                if (currentExercise.notes.isNotEmpty()) {
                                    Text(
                                        text = currentExercise.notes,
                                        style = MaterialTheme.typography.caption2,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                                if (currentExercise.supersetId != null) {
                                     Text(
                                        text = "Superset ${currentExercise.supersetId}",
                                        style = MaterialTheme.typography.caption2,
                                        color = Color(0xFFFF9800),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                }
                            }
                        }

                        // Stats Row (Timer, BPM, Cals)
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Workout Timer / Rest Timer
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    if (restTimerSeconds > 0) {
                                        Text(
                                            text = formatTime(restTimerSeconds),
                                            color = Color(0xFF4C6EF5), // Blue for rest
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text("Rest", color = Color.Gray, fontSize = 10.sp)
                                    } else {
                                        Text(
                                            text = formatTime(timerSeconds),
                                            color = if (isTimerRunning) Color.Green else Color.Gray,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text("Time", color = Color.Gray, fontSize = 10.sp)
                                    }
                                }
                                
                                // Heart Rate
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "$heartRate",
                                        color = Color(0xFFE91E63),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text("BPM", color = Color.Gray, fontSize = 10.sp)
                                }
                                
                                // Calories
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "$calories",
                                        color = Color(0xFFFF9800),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text("Kcal", color = Color.Gray, fontSize = 10.sp)
                                }
                            }
                        }

                        // Column Headers
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Kg", color = Color(0xFF4C6EF5), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                Text("Reps", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                                Text("RPE", color = Color(0xFFFF9800), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.8f), textAlign = TextAlign.Center)
                                Spacer(modifier = Modifier.width(24.dp)) // Checkbox space
                            }
                        }

                        // Sets
                        itemsIndexed(currentExercise.sets) { index, set ->
                            WearSetItem(
                                index = index + 1,
                                set = set,
                                onToggle = { onSetClick(currentExercise.exerciseId, index, !set.completed) },
                                onEdit = { editingSet = Triple(currentExercise.exerciseId, index, set) }
                            )
                        }

                        // Add Set Button
                        item {
                            Button(
                                onClick = { onAddSet(currentExercise.exerciseId) },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                                modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 16.dp)
                            ) {
                                Text("Add Set", fontSize = 12.sp)
                            }
                        }
                        
                        // Exercise Options Button
                        item {
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                Button(
                                    onClick = { showExerciseOptions = true }, 
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                                    modifier = Modifier.size(40.dp).clip(CircleShape)
                                ) {
                                    Text("...", color = Color.White)
                                }
                            }
                        }
                    }
                    
                    // Exercise Options Dialog
                    if (showExerciseOptions) {
                        Dialog(
                            onDismissRequest = { showExerciseOptions = false }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black)
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("Opciones", style = MaterialTheme.typography.title3)
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { 
                                        onReplaceExercise(page) // Use page index
                                        showExerciseOptions = false
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Reemplazar Ejercicio", fontSize = 12.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Button(
                                    onClick = { 
                                        onDeleteExercise(page) // Use page index
                                        showExerciseOptions = false 
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD32F2F)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Eliminar Ejercicio", fontSize = 12.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Button(
                                    onClick = { showExerciseOptions = false },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Cancelar", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                } else {
                    // Last Page: Add Exercise / Finish
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = onAddExerciseClick,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                            modifier = Modifier.fillMaxWidth(0.8f)
                        ) {
                            Text("Add Exercise")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onFinishWorkout,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C6EF5)),
                            modifier = Modifier.fillMaxWidth(0.8f)
                        ) {
                            Text("Finish Workout")
                        }
                    }
                }
            }
            
            // Page Indicator (Dots)
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(workout.exercises.size + 1) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White else Color.DarkGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(6.dp)
                    )
                }
            }

        } else {
             // No exercises
             Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No exercises", color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onAddExerciseClick) {
                    Text("Add Exercise")
                }
            }
        }
    }
}

@Composable
fun WearSetItem(index: Int, set: WorkoutSet, onToggle: () -> Unit, onEdit: () -> Unit) {
    val backgroundColor = if (set.completed) Color(0xFF1B5E20) else Color(0xFF212121)
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onEdit) // Edit on tap anywhere except checkbox
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Index & Type Indicator
        Column(modifier = Modifier.width(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$index",
                color = Color.Gray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            if (set.type != SetType.NORMAL) {
                Text(
                    text = set.type.name.take(1), // W, D, F
                    color = Color(0xFFFF9800),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Current Weight
        Text(
            text = "${set.weight}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // Current Reps
        Text(
            text = "${set.reps}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // RPE
        Text(
            text = if (set.rpe != null) "${set.rpe}" else "-",
            color = Color(0xFFFF9800),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.8f),
            textAlign = TextAlign.Center
        )

        // Checkbox
        Box(
            modifier = Modifier
                .size(32.dp)
                .clickable(onClick = onToggle) // Only toggle on checkbox click
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
             Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (set.completed) Color(0xFF4C6EF5) else Color.Transparent)
                    .border(2.dp, if (set.completed) Color(0xFF4C6EF5) else Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (set.completed) {
                    Text("✓", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}
