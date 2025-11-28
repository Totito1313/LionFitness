package com.schwarckstudio.lionfitness.ui.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Equipment
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.ExerciseType
import com.schwarckstudio.lionfitness.core.model.MuscleGroup
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCustomExerciseScreen(
    viewModel: ExerciseViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var selectedEquipment by remember { mutableStateOf(Equipment.NONE) }
    var selectedMuscle by remember { mutableStateOf(MuscleGroup.UNKNOWN) }
    var selectedType by remember { mutableStateOf(ExerciseType.WEIGHT_AND_REPS) }
    val selectedSecondaryMuscles = remember { mutableStateListOf<MuscleGroup>() }
    
    var showEquipmentSheet by remember { mutableStateOf(false) }
    var showMuscleSheet by remember { mutableStateOf(false) }
    var showSecondaryMuscleSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Ejercicio", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            if (name.isNotBlank()) {
                                val newExercise = Exercise(
                                    id = UUID.randomUUID().toString(),
                                    name = name,
                                    equipment = selectedEquipment,
                                    primaryMuscle = selectedMuscle,
                                    secondaryMuscles = selectedSecondaryMuscles.toList(),
                                    type = selectedType,
                                    isCustom = true
                                )
                                viewModel.saveCustomExercise(newExercise)
                                onNavigateBack()
                            }
                        },
                        enabled = name.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DesignSystem.Colors.Primary,
                            disabledContainerColor = DesignSystem.Colors.Primary.copy(alpha = 0.5f)
                        ),
                        shape = DesignSystem.Shapes.Button
                    ) {
                        Text("Guardar", fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DesignSystem.Colors.Background,
                    titleContentColor = DesignSystem.Colors.TextPrimary,
                    navigationIconContentColor = DesignSystem.Colors.TextPrimary
                )
            )
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
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre del Ejercicio") },
                modifier = Modifier.fillMaxWidth(),
                shape = DesignSystem.Shapes.Input,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DesignSystem.Colors.Surface,
                    unfocusedContainerColor = DesignSystem.Colors.Surface,
                    disabledContainerColor = DesignSystem.Colors.Surface,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
            
            SelectionRow(
                label = "Equipo",
                value = selectedEquipment.displayName,
                onClick = { showEquipmentSheet = true }
            )
            
            SelectionRow(
                label = "Músculo Principal",
                value = selectedMuscle.displayName,
                onClick = { showMuscleSheet = true }
            )

            SelectionRow(
                label = "Músculos Secundarios",
                value = if (selectedSecondaryMuscles.isEmpty()) "Ninguno" else selectedSecondaryMuscles.joinToString { it.displayName },
                onClick = { showSecondaryMuscleSheet = true }
            )
            
            Text(
                "Tipo de Ejercicio", 
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DesignSystem.Colors.TextPrimary
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(ExerciseType.values()) { type ->
                    TypeCard(
                        type = type,
                        isSelected = type == selectedType,
                        onClick = { selectedType = type }
                    )
                }
            }
        }
        
        if (showEquipmentSheet) {
            SingleSelectionSheet(
                title = "Seleccionar Equipo",
                options = Equipment.values().toList(),
                selectedOption = selectedEquipment,
                onOptionSelected = { 
                    selectedEquipment = it
                    showEquipmentSheet = false
                },
                onDismiss = { showEquipmentSheet = false },
                displayName = { it.displayName }
            )
        }
        
        if (showMuscleSheet) {
            SingleSelectionSheet(
                title = "Seleccionar Músculo Principal",
                options = MuscleGroup.values().toList(),
                selectedOption = selectedMuscle,
                onOptionSelected = { 
                    selectedMuscle = it
                    showMuscleSheet = false
                },
                onDismiss = { showMuscleSheet = false },
                displayName = { it.displayName }
            )
        }

        if (showSecondaryMuscleSheet) {
            MultiSelectionSheet(
                title = "Seleccionar Músculos Secundarios",
                options = MuscleGroup.values().toList(),
                selectedOptions = selectedSecondaryMuscles,
                onOptionToggle = { muscle ->
                    if (selectedSecondaryMuscles.contains(muscle)) {
                        selectedSecondaryMuscles.remove(muscle)
                    } else {
                        selectedSecondaryMuscles.add(muscle)
                    }
                },
                onDismiss = { showSecondaryMuscleSheet = false },
                displayName = { it.displayName }
            )
        }
    }
}

@Composable
fun SelectionRow(label: String, value: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = DesignSystem.Colors.Surface
        ),
        shape = DesignSystem.Shapes.Card
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(label, style = MaterialTheme.typography.bodySmall, color = DesignSystem.Colors.TextSecondary)
                Text(value, style = MaterialTheme.typography.bodyLarge, color = DesignSystem.Colors.TextPrimary, fontWeight = FontWeight.SemiBold)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = DesignSystem.Colors.TextSecondary)
        }
    }
}

@Composable
fun TypeCard(type: ExerciseType, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) DesignSystem.Colors.Primary else Color.Transparent,
                shape = DesignSystem.Shapes.Card
            ),
        colors = CardDefaults.cardColors(
            containerColor = DesignSystem.Colors.Surface
        ),
        shape = DesignSystem.Shapes.Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                type.displayName, 
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) DesignSystem.Colors.Primary else DesignSystem.Colors.TextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                type.units.joinToString(" • ") { it.uppercase() },
                style = MaterialTheme.typography.bodySmall,
                color = DesignSystem.Colors.TextSecondary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SingleSelectionSheet(
    title: String,
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    onDismiss: () -> Unit,
    displayName: (T) -> String
) {
    val sheetState = rememberModalBottomSheetState()
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = DesignSystem.Colors.Background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title, 
                style = MaterialTheme.typography.titleLarge, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(options) { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOptionSelected(option) }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            displayName(option),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = if (option == selectedOption) FontWeight.Bold else FontWeight.Normal,
                            color = if (option == selectedOption) DesignSystem.Colors.Primary else DesignSystem.Colors.TextPrimary
                        )
                        if (option == selectedOption) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = DesignSystem.Colors.Primary)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MultiSelectionSheet(
    title: String,
    options: List<T>,
    selectedOptions: List<T>,
    onOptionToggle: (T) -> Unit,
    onDismiss: () -> Unit,
    displayName: (T) -> String
) {
    val sheetState = rememberModalBottomSheetState()
    
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = DesignSystem.Colors.Background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title, 
                style = MaterialTheme.typography.titleLarge, 
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(options) { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOptionToggle(option) }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            displayName(option),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = if (selectedOptions.contains(option)) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedOptions.contains(option)) DesignSystem.Colors.Primary else DesignSystem.Colors.TextPrimary
                        )
                        if (selectedOptions.contains(option)) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = DesignSystem.Colors.Primary)
                        }
                    }
                }
            }
        }
    }
}
