package com.schwarckstudio.lionfitness.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.schwarckstudio.lionfitness.logic.PlateCalculator
import com.schwarckstudio.lionfitness.ui.components.LionDialog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import androidx.compose.material3.OutlinedTextFieldDefaults

@Composable
fun PlateCalculatorDialog(
    onDismiss: () -> Unit
) {
    var weightInput by remember { mutableStateOf("") }
    var plates by remember { mutableStateOf<List<Double>>(emptyList()) }

    LionDialog(
        onDismissRequest = onDismiss,
        title = "Calculadora de Discos",
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                 OutlinedTextField(
                    value = weightInput,
                    onValueChange = { 
                        weightInput = it
                        val weight = it.toDoubleOrNull() ?: 0.0
                        plates = PlateCalculator.calculatePlates(weight)
                    },
                    label = { Text("Peso Objetivo (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = DesignSystem.Shapes.Input,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DesignSystem.Colors.Primary,
                        focusedLabelColor = DesignSystem.Colors.Primary
                    )
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                if (plates.isNotEmpty()) {
                    Text("Por Lado:", style = MaterialTheme.typography.titleMedium, color = DesignSystem.Colors.TextPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        plates.forEach { plate ->
                            PlateItem(weight = plate)
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                } else if (weightInput.isNotEmpty() && (weightInput.toDoubleOrNull() ?: 0.0) < 20.0) {
                     Text("El peso debe ser > 20kg (Barra)", color = MaterialTheme.colorScheme.error)
                }
            }
        },
        dismissButtonText = "Cerrar",
        onDismiss = onDismiss
    )
}

@Composable
fun PlateItem(weight: Double) {
    val color = when (weight) {
        25.0 -> Color.Red
        20.0 -> Color.Blue
        15.0 -> Color.Yellow
        10.0 -> Color.Green
        5.0 -> Color.White
        2.5 -> Color.Black
        else -> Color.Gray
    }
    
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color)
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = weight.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = if (color == Color.White) Color.Black else Color.White
        )
    }
}
