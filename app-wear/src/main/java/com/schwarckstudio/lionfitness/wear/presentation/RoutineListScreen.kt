package com.schwarckstudio.lionfitness.wear.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import com.schwarckstudio.lionfitness.core.model.Routine

@Composable
fun RoutineListScreen(
    routines: List<Routine>,
    onRoutineClick: (Routine) -> Unit,
    onEmptyWorkoutClick: () -> Unit,
    showContinueButton: Boolean,
    onContinueClick: () -> Unit,
    onForceSyncClick: () -> Unit = {}
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 30.dp, 
            bottom = 30.dp,
            start = 8.dp,
            end = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Seleccionar Rutina",
                style = MaterialTheme.typography.title2,
                color = MaterialTheme.colors.primary
            )
        }

        if (showContinueButton) {
            item {
                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Continuar Entrenamiento") },
                    onClick = onContinueClick,
                    colors = ChipDefaults.secondaryChipColors(
                        backgroundColor = Color(0xFF4C6EF5), // Lion Blue
                        contentColor = Color.White
                    )
                )
            }
        }

        item {
            Chip(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Entrenamiento Vacío") },
                onClick = onEmptyWorkoutClick,
                colors = ChipDefaults.secondaryChipColors()
            )
        }

        items(routines) { routine ->
            Chip(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(routine.name) },
                onClick = { onRoutineClick(routine) },
                colors = ChipDefaults.primaryChipColors()
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onForceSyncClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.schwarckstudio.lionfitness.wear.R.drawable.ic_sync),
                    contentDescription = "Sincronizar",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Sincronizar", fontSize = 10.sp)
            }
        }
    }
}
