package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.core.model.RoutineExercise
import com.schwarckstudio.lionfitness.core.model.RoutineSetTemplate
import com.schwarckstudio.lionfitness.core.model.SetType
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import java.util.UUID

data class RoutineExerciseUiState(
    val exercise: Exercise,
    val sets: MutableList<RoutineSetTemplate> = mutableStateListOf(),
    var notes: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineScreen(
    routineViewModel: RoutineViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToAddExercise: () -> Unit,
    newlySelectedExercises: List<Exercise>? = null
) {
    var routineName by remember { mutableStateOf("") }
    var routineNotes by remember { mutableStateOf("") }
    val exerciseStates = remember { mutableStateListOf<RoutineExerciseUiState>() }
    
    LaunchedEffect(newlySelectedExercises) {
        if (newlySelectedExercises != null) {
            newlySelectedExercises.forEach { newExercise ->
                if (exerciseStates.none { it.exercise.id == newExercise.id }) {
                    exerciseStates.add(RoutineExerciseUiState(exercise = newExercise))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Rutina", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DesignSystem.Colors.Background,
                    titleContentColor = DesignSystem.Colors.TextPrimary,
                    navigationIconContentColor = DesignSystem.Colors.TextPrimary,
                    actionIconContentColor = DesignSystem.Colors.Primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            if (routineName.isNotBlank()) {
                                val newRoutine = Routine(
                                    id = UUID.randomUUID().toString(),
                                    name = routineName,
                                    notes = routineNotes,
                                    exercises = exerciseStates.map { state ->
                                        RoutineExercise(
                                            exerciseId = state.exercise.id,
                                            exerciseName = state.exercise.name, // Denormalize name
                                            notes = state.notes,
                                            templateSets = state.sets.toList()
                                        )
                                    }
                                )
                                routineViewModel.saveRoutine(newRoutine)
                                onNavigateBack()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Save")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddExercise,
                containerColor = DesignSystem.Colors.Primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Exercise")
            }
        },
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = routineName,
                        onValueChange = { routineName = it },
                        label = { Text("Nombre de la Rutina") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    OutlinedTextField(
                        value = routineNotes,
                        onValueChange = { routineNotes = it },
                        label = { Text("Notas (Opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        minLines = 2
                    )
                }
            }
            
            Text(
                "Ejercicios", 
                fontSize = 18.sp, 
                fontWeight = FontWeight.Bold, 
                color = DesignSystem.Colors.TextPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(exerciseStates) { state ->
                    RoutineExerciseItem(
                        state = state,
                        onRemove = { exerciseStates.remove(state) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(80.dp)) // Bottom padding for FAB
                }
            }
        }
    }
}

@Composable
fun RoutineExerciseItem(
    state: RoutineExerciseUiState,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(DesignSystem.Colors.Primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.exercise.name.firstOrNull()?.toString() ?: "E",
                            fontWeight = FontWeight.Bold,
                            color = DesignSystem.Colors.Primary
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        state.exercise.name, 
                        fontSize = 16.sp, 
                        fontWeight = FontWeight.Bold,
                        color = DesignSystem.Colors.TextPrimary
                    )
                }
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color.Red.copy(alpha = 0.7f))
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            state.sets.forEachIndexed { index, set ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${index + 1}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    }
                    
                    OutlinedTextField(
                        value = set.targetReps,
                        onValueChange = { newValue -> 
                            state.sets[index] = set.copy(targetReps = newValue)
                        },
                        label = { Text("Reps") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    OutlinedTextField(
                        value = set.targetWeight,
                        onValueChange = { newValue -> 
                            state.sets[index] = set.copy(targetWeight = newValue)
                        },
                        label = { Text("Kg") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp)
                    )
                    
                    IconButton(onClick = { state.sets.removeAt(index) }, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Close, contentDescription = "Remove Set", tint = Color.Gray)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { 
                    state.sets.add(RoutineSetTemplate(type = SetType.NORMAL)) 
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DesignSystem.Colors.Primary.copy(alpha = 0.1f),
                    contentColor = DesignSystem.Colors.Primary
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Agregar Serie")
            }
        }
    }
}
