package com.schwarckstudio.lionfitness.wear.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Picker
import androidx.wear.compose.material.PickerState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberPickerState
import kotlinx.coroutines.launch
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.focusable

@Composable
fun WearNumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    label: String,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val state = rememberPickerState(
        initialNumberOfOptions = range.last - range.first + 1,
        initiallySelectedOption = value - range.first
    )
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(state.selectedOption) {
        onValueChange(state.selectedOption + range.first)
    }
    
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, style = MaterialTheme.typography.caption2, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Picker(
            state = state,
            contentDescription = label,
            modifier = Modifier
                .size(60.dp, 100.dp)
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        if (it.verticalScrollPixels > 0) {
                            state.scrollToOption(state.selectedOption + 1)
                        } else {
                            state.scrollToOption(state.selectedOption - 1)
                        }
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable()
        ) {
            val currentVal = it + range.first
            Text(
                text = "$currentVal",
                style = if (it == state.selectedOption) {
                    TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4C6EF5))
                } else {
                    TextStyle(fontSize = 16.sp, color = Color.Gray)
                }
            )
        }
    }
}

@Composable
fun WearDecimalPicker(
    value: Double,
    onValueChange: (Double) -> Unit,
    range: IntRange, // Range for the integer part
    label: String,
    modifier: Modifier = Modifier
) {
    val integerPart = value.toInt()
    val decimalPart = ((value - integerPart) * 100).toInt() // 0, 25, 50, 75

    val integerState = rememberPickerState(
        initialNumberOfOptions = range.last - range.first + 1,
        initiallySelectedOption = integerPart - range.first
    )
    
    val decimals = listOf(0, 25, 50, 75)
    val decimalState = rememberPickerState(
        initialNumberOfOptions = decimals.size,
        initiallySelectedOption = decimals.indexOfFirst { it == decimalPart }.coerceAtLeast(0)
    )
    
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    // Accumulator for scroll events to make it smoother/less sensitive
    var scrollAccumulator by remember { mutableStateOf(0f) }
    val scrollThreshold = 40f // Adjust sensitivity here

    LaunchedEffect(integerState.selectedOption, decimalState.selectedOption) {
        val newInt = integerState.selectedOption + range.first
        val newDec = decimals[decimalState.selectedOption] / 100.0
        onValueChange(newInt + newDec)
    }
    
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, style = MaterialTheme.typography.caption2, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        scrollAccumulator += it.verticalScrollPixels
                        if (kotlin.math.abs(scrollAccumulator) > scrollThreshold) {
                            val direction = if (scrollAccumulator > 0) 1 else -1
                            scrollAccumulator = 0f
                            
                            if (direction > 0) {
                                // Increment Integer
                                if (integerState.selectedOption < range.last - range.first) {
                                    integerState.scrollToOption(integerState.selectedOption + 1)
                                }
                            } else {
                                // Decrement Integer
                                if (integerState.selectedOption > 0) {
                                    integerState.scrollToOption(integerState.selectedOption - 1)
                                }
                            }
                        }
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable()
        ) {
            Picker(
                state = integerState,
                contentDescription = "Integer",
                modifier = Modifier.size(40.dp, 100.dp)
            ) {
                val currentVal = it + range.first
                Text(
                    text = "$currentVal",
                    style = if (it == integerState.selectedOption) {
                        TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4C6EF5))
                    } else {
                        TextStyle(fontSize = 16.sp, color = Color.Gray)
                    }
                )
            }
            Text(".", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White))
            Picker(
                state = decimalState,
                contentDescription = "Decimal",
                modifier = Modifier.size(40.dp, 100.dp)
            ) {
                val currentVal = decimals[it]
                Text(
                    text = "$currentVal",
                    style = if (it == decimalState.selectedOption) {
                        TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4C6EF5))
                    } else {
                        TextStyle(fontSize = 16.sp, color = Color.Gray)
                    }
                )
            }
        }
    }
}
