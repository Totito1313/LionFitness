package com.schwarckstudio.lionfitness.wear.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.schwarckstudio.lionfitness.wear.R
import com.schwarckstudio.lionfitness.core.model.WorkoutLog

@Composable
fun FinishWorkoutScreen(
    workout: WorkoutLog?,
    onSaveClick: (String) -> Unit,
    onDiscardClick: () -> Unit
) {
    if (workout == null) return

    var workoutName by remember { mutableStateOf(workout.name) }
    val nameOptions = listOf("Entrenamiento Libre", "Tren Superior", "Tren Inferior", "Cuerpo Completo", "Cardio", "Crossfit", "Brazo", "Pecho", "Espalda", "Pierna")
    
    // If the current name is not in the list, add it so we don't lose it when cycling
    val currentOptions = remember(workout.name) {
        if (workout.name !in nameOptions) listOf(workout.name) + nameOptions else nameOptions
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resumen",
            style = MaterialTheme.typography.title2,
            color = Color(0xFF4C6EF5)
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        // Name Editor
        Text(
            text = workoutName,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Chip(
            onClick = {
                // Cycle through names
                val currentIndex = currentOptions.indexOf(workoutName)
                val nextIndex = (currentIndex + 1) % currentOptions.size
                workoutName = currentOptions[nextIndex]
            },
            label = { 
                Text(
                    text = "Cambiar Nombre", 
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 10.sp
                ) 
            },
            colors = ChipDefaults.secondaryChipColors(),
            modifier = Modifier.height(32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("${workout.exercises.size}", style = MaterialTheme.typography.title3, color = Color.White)
                Text("Ejercicios", style = MaterialTheme.typography.caption2, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("${workout.totalSets}", style = MaterialTheme.typography.title3, color = Color.White)
                Text("Series", style = MaterialTheme.typography.caption2, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSaveClick(workoutName) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C6EF5)),
            modifier = Modifier.fillMaxWidth().height(40.dp)
        ) {
            Text("Guardar")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = onDiscardClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
            modifier = Modifier.height(32.dp)
        ) {
            Text("Descartar", fontSize = 10.sp)
        }
    }
}
