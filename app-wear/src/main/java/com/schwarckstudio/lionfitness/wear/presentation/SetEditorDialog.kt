package com.schwarckstudio.lionfitness.wear.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Dialog
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.compose.foundation.gestures.scrollBy
import kotlinx.coroutines.launch

@Composable
fun SetEditorDialog(
    showDialog: Boolean,
    initialWeight: Double,
    initialReps: Int,
    initialRpe: Int?,
    initialType: com.schwarckstudio.lionfitness.core.model.SetType,
    onDismiss: () -> Unit,
    onConfirm: (weight: Double, reps: Int, rpe: Int?, type: com.schwarckstudio.lionfitness.core.model.SetType) -> Unit
) {
    var weight by remember(initialWeight) { mutableStateOf(initialWeight) }
    var reps by remember(initialReps) { mutableStateOf(initialReps) }
    var rpe by remember(initialRpe) { mutableStateOf(initialRpe ?: 0) }
    var type by remember(initialType) { mutableStateOf(initialType) }
    
    // Field selection: -1 = Overview, 0 = Weight, 1 = Reps, 2 = RPE
    var selectedField by remember { mutableStateOf(-1) }
    
    val listState = rememberScalingLazyListState()
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Dialog(
        showDialog = showDialog,
        onDismissRequest = onDismiss
    ) {
        // If a field is selected for editing, show the picker
        if (selectedField != -1) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when (selectedField) {
                        0 -> { // Weight
                            WearDecimalPicker(
                                value = weight,
                                onValueChange = { weight = it },
                                range = 0..500,
                                label = "PESO (kg)"
                            )
                        }
                        1 -> { // Reps
                            WearNumberPicker(
                                value = reps,
                                onValueChange = { reps = it },
                                range = 0..100,
                                label = "REPS"
                            )
                        }
                        2 -> { // RPE
                            WearNumberPicker(
                                value = rpe,
                                onValueChange = { rpe = it },
                                range = 0..10,
                                label = "RPE"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { selectedField = -1 }, // Go back to overview
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C6EF5)),
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                    ) {
                        Text("✓", color = Color.White)
                    }
                }
            }
        } else {
            // Overview Screen with Scrolling
            ScalingLazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .onRotaryScrollEvent {
                        coroutineScope.launch {
                            listState.scrollBy(it.verticalScrollPixels)
                        }
                        true
                    }
                    .focusRequester(focusRequester)
                    .focusable(),
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "Editar Serie", 
                        style = MaterialTheme.typography.title3,
                        color = Color(0xFFE0E0E0),
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                // Weight
                item {
                    EditorRow(
                        label = "PESO",
                        value = "${weight}kg",
                        onClick = { selectedField = 0 }
                    )
                }
                
                // Reps
                item {
                    EditorRow(
                        label = "REPS",
                        value = "$reps",
                        onClick = { selectedField = 1 }
                    )
                }

                // RPE
                item {
                    EditorRow(
                        label = "RPE",
                        value = if (rpe > 0) "$rpe" else "-",
                        onClick = { selectedField = 2 }
                    )
                }

                // Set Type
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val types = listOf("N", "W", "D", "F")
                        val typeEnums = listOf(com.schwarckstudio.lionfitness.core.model.SetType.NORMAL, com.schwarckstudio.lionfitness.core.model.SetType.WARMUP, com.schwarckstudio.lionfitness.core.model.SetType.DROP_SET, com.schwarckstudio.lionfitness.core.model.SetType.FAILURE)
                        
                        types.forEachIndexed { index, label ->
                            val isSelected = type == typeEnums[index]
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) Color(0xFF4C6EF5) else Color(0xFF333333))
                                    .clickable { type = typeEnums[index] },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    color = if (isSelected) Color.White else Color.Gray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                item {
                    Row(modifier = Modifier.padding(bottom = 20.dp)) {
                        Button(
                            onClick = onDismiss,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF333333)),
                            modifier = Modifier.size(48.dp).clip(CircleShape)
                        ) {
                            Text("✕", color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = { onConfirm(weight, reps, if (rpe > 0) rpe else null, type) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4C6EF5)), // Lion Blue
                            modifier = Modifier.size(48.dp).clip(CircleShape)
                        ) {
                            Text("✓", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EditorRow(
    label: String,
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF212121))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption1,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
