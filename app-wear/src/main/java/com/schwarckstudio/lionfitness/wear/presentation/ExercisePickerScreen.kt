package com.schwarckstudio.lionfitness.wear.presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import com.schwarckstudio.lionfitness.core.model.Exercise

@Composable
fun ExercisePickerScreen(
    exercises: List<Exercise>,
    onExerciseClick: (Exercise) -> Unit,
    onCancel: () -> Unit
) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            ScalingLazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 40.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 40.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "Agregar Ejercicio",
                        style = MaterialTheme.typography.title2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                items(exercises) { exercise ->
                    Card(
                        onClick = { onExerciseClick(exercise) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = exercise.name,
                                style = MaterialTheme.typography.body1
                            )
                            Text(
                                text = exercise.primaryMuscle.name,
                                style = MaterialTheme.typography.caption2,
                                color = Color.Gray
                            )
                        }
                    }
                }
                
                item {
                    Card(
                        onClick = onCancel,
                        modifier = Modifier.fillMaxWidth(),
                        backgroundPainter = androidx.wear.compose.material.CardDefaults.cardBackgroundPainter(
                            startBackgroundColor = Color.DarkGray,
                            endBackgroundColor = Color.DarkGray
                        )
                    ) {
                        Text(
                            text = "Cancelar",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
