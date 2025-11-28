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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Equipment
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.MuscleGroup
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseScreen(
    viewModel: ExerciseViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onCreateCustomExercise: () -> Unit,
    onAddExercises: (List<Exercise>) -> Unit
) {
    val exercises by viewModel.filteredExercises.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedEquipment by viewModel.selectedEquipment.collectAsState()
    val selectedMuscles by viewModel.selectedMuscles.collectAsState()

    val selectedExercises = remember { mutableStateListOf<Exercise>() }
    
    var showEquipmentSheet by remember { mutableStateOf(false) }
    var showMuscleSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Añadir Ejercicios", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                actions = {
                    TextButton(onClick = onCreateCustomExercise) {
                        Text("Crear", color = DesignSystem.Colors.Primary, fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DesignSystem.Colors.Background,
                    titleContentColor = DesignSystem.Colors.TextPrimary,
                    navigationIconContentColor = DesignSystem.Colors.TextPrimary
                )
            )
        },
        bottomBar = {
            if (selectedExercises.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DesignSystem.Colors.Background)
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = { onAddExercises(ArrayList(selectedExercises)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DesignSystem.Colors.Primary
                        ),
                        shape = DesignSystem.Shapes.Button
                    ) {
                        Text("Añadir ${selectedExercises.size} Ejercicios", fontWeight = FontWeight.Bold)
                    }
                }
            }
        },
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Buscar ejercicio") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = DesignSystem.Colors.TextSecondary) },
                shape = DesignSystem.Shapes.Input,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DesignSystem.Colors.Surface,
                    unfocusedContainerColor = DesignSystem.Colors.Surface,
                    disabledContainerColor = DesignSystem.Colors.Surface,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            // Filters
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedEquipment.isNotEmpty(),
                        onClick = { showEquipmentSheet = true },
                        label = { Text(if (selectedEquipment.isEmpty()) "Equipo" else "${selectedEquipment.size} Equipo") },
                        leadingIcon = { Icon(Icons.Default.FilterList, contentDescription = null) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DesignSystem.Colors.Primary.copy(alpha = 0.2f),
                            selectedLabelColor = DesignSystem.Colors.Primary,
                            selectedLeadingIconColor = DesignSystem.Colors.Primary
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = selectedMuscles.isNotEmpty(),
                        onClick = { showMuscleSheet = true },
                        label = { Text(if (selectedMuscles.isEmpty()) "Músculos" else "${selectedMuscles.size} Músculos") },
                        leadingIcon = { Icon(Icons.Default.FilterList, contentDescription = null) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DesignSystem.Colors.Primary.copy(alpha = 0.2f),
                            selectedLabelColor = DesignSystem.Colors.Primary,
                            selectedLeadingIconColor = DesignSystem.Colors.Primary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Exercise List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 80.dp)
            ) {
                items(exercises) { exercise ->
                    ExerciseSelectionItem(
                        exercise = exercise,
                        isSelected = selectedExercises.contains(exercise),
                        onToggle = {
                            if (selectedExercises.contains(exercise)) {
                                selectedExercises.remove(exercise)
                            } else {
                                selectedExercises.add(exercise)
                            }
                        }
                    )
                }
            }
        }
        
        if (showEquipmentSheet) {
            FilterSheet(
                title = "Filtrar por Equipo",
                options = Equipment.values().toList(),
                selectedOptions = selectedEquipment,
                onOptionToggle = { viewModel.toggleEquipmentFilter(it) },
                onDismiss = { showEquipmentSheet = false },
                displayName = { it.displayName }
            )
        }
        
        if (showMuscleSheet) {
            FilterSheet(
                title = "Filtrar por Músculo",
                options = MuscleGroup.values().toList(),
                selectedOptions = selectedMuscles,
                onOptionToggle = { viewModel.toggleMuscleFilter(it) },
                onDismiss = { showMuscleSheet = false },
                displayName = { it.displayName }
            )
        }
    }
}

@Composable
fun ExerciseSelectionItem(
    exercise: Exercise,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable(onClick = onToggle)
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder Image or Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(DesignSystem.Colors.Primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = exercise.name.take(1).uppercase(),
                    color = DesignSystem.Colors.Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = DesignSystem.Colors.TextPrimary
                )
                Text(
                    text = exercise.primaryMuscle.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = DesignSystem.Colors.TextSecondary
                )
            }
            
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = DesignSystem.Colors.Primary
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = DesignSystem.Colors.TextSecondary.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FilterSheet(
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
                text = title,
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
                            text = displayName(option),
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (selectedOptions.contains(option)) DesignSystem.Colors.Primary else DesignSystem.Colors.TextPrimary,
                            fontWeight = if (selectedOptions.contains(option)) FontWeight.Bold else FontWeight.Normal
                        )
                        if (selectedOptions.contains(option)) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = DesignSystem.Colors.Primary
                            )
                        }
                    }
                }
            }
        }
    }
}
