package com.schwarckstudio.lionfitness.ui.screens.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.core.model.MuscleGroup
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun ExerciseListScreen(
    viewModel: ExerciseViewModel = hiltViewModel(),
    onExerciseClick: (String) -> Unit,
    onNavigateToCreateCustomExercise: () -> Unit,
    isSelectionMode: Boolean = false,
    onExercisesSelected: (List<Exercise>) -> Unit = {}
) {
    val exercises by viewModel.filteredExercises.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedMuscles by viewModel.selectedMuscles.collectAsState()
    
    // Selection state
    val selectedExerciseIds = remember { mutableStateListOf<String>() }

    // TopBar state managed by LionFitnessApp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DesignSystem.Colors.Surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top App Bar removed
            // Top App Bar removed


            // Scrollable Content
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 70.dp, bottom = 100.dp)
            ) {
                item {
                    SearchBar(
                        searchTerm = searchQuery,
                        onSearchTermChange = { viewModel.onSearchQueryChange(it) }
                    )
                }

                item {
                    FilterBar(
                        selectedMuscles = selectedMuscles,
                        onMuscleSelected = { muscle ->
                            // Implement single selection behavior for UI consistency with design
                            viewModel.clearFilters()
                            if (muscle != null) {
                                viewModel.toggleMuscleFilter(muscle)
                            }
                        }
                    )
                }
                
                item {
                    PaddingValues(horizontal = 18.dp, vertical = 8.dp).let {
                        Text(
                            text = "${exercises.size} ejercicios encontrados",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = DesignSystem.Colors.TextSecondary,
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                        )
                    }
                }

                if (exercises.isEmpty()) {
                    item {
                        EmptyState()
                    }
                } else {
                    items(exercises) { exercise ->
                        val isSelected = selectedExerciseIds.contains(exercise.id)
                        ExerciseCard(
                            exercise = exercise,
                            onClick = {
                                if (isSelectionMode) {
                                    if (isSelected) {
                                        selectedExerciseIds.remove(exercise.id)
                                    } else {
                                        selectedExerciseIds.add(exercise.id)
                                    }
                                } else {
                                    onExerciseClick(exercise.id)
                                }
                            },
                            isSelectionMode = isSelectionMode,
                            isSelected = isSelected
                        )
                    }
                }
            }
        }

        if (isSelectionMode && selectedExerciseIds.isNotEmpty()) {
            Button(
                onClick = { 
                    val selected = exercises.filter { it.id in selectedExerciseIds }
                    onExercisesSelected(selected) 
                },
                colors = ButtonDefaults.buttonColors(containerColor = DesignSystem.Colors.Primary),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .padding(bottom = 80.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Añadir (${selectedExerciseIds.size})")
            }
        }
    }
}

@Composable
fun SearchBar(searchTerm: String, onSearchTermChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = DesignSystem.Colors.TextSecondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        androidx.compose.foundation.text.BasicTextField(
            value = searchTerm,
            onValueChange = onSearchTermChange,
            modifier = Modifier.weight(1f),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 15.sp,
                color = DesignSystem.Colors.TextPrimary,
                fontWeight = FontWeight.Medium
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (searchTerm.isEmpty()) {
                    Text(
                        text = "Buscar ejercicios...",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 15.sp,
                            color = DesignSystem.Colors.TextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                innerTextField()
            }
        )
        if (searchTerm.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.05f))
                    .clickable { onSearchTermChange("") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear",
                    tint = DesignSystem.Colors.TextSecondary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
fun FilterBar(
    selectedMuscles: List<MuscleGroup>,
    onMuscleSelected: (MuscleGroup?) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = null,
                tint = DesignSystem.Colors.TextSecondary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Filtrar por grupo muscular",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DesignSystem.Colors.TextPrimary
            )
        }
        
        LazyRow(
            contentPadding = PaddingValues(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    label = "Todos",
                    isActive = selectedMuscles.isEmpty(),
                    onClick = { onMuscleSelected(null) }
                )
            }
            items(MuscleGroup.values().filter { it != MuscleGroup.UNKNOWN }) { muscle ->
                FilterChip(
                    label = muscle.displayName,
                    isActive = selectedMuscles.contains(muscle),
                    onClick = { onMuscleSelected(muscle) }
                )
            }
        }
    }
}

@Composable
fun FilterChip(label: String, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isActive) DesignSystem.Colors.Primary else Color.Black.copy(alpha = 0.05f)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isActive) Color.White else DesignSystem.Colors.TextSecondary
        )
    }
}

@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit,
    isSelectionMode: Boolean,
    isSelected: Boolean
) {
    val gradientColors = getMuscleGradient(exercise.primaryMuscle)
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected && isSelectionMode) DesignSystem.Colors.Primary.copy(alpha = 0.1f) 
                else Color.White.copy(alpha = 0.7f)
            )
            .border(
                width = if (isSelected && isSelectionMode) 2.dp else 0.dp,
                color = if (isSelected && isSelectionMode) DesignSystem.Colors.Primary else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(14.dp))
        
        // Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DesignSystem.Colors.TextPrimary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Target Icon placeholder if needed
                 Text(
                    text = exercise.primaryMuscle.displayName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DesignSystem.Colors.TextSecondary
                )
                Text(
                    text = " • ",
                    color = DesignSystem.Colors.TextSecondary.copy(alpha = 0.5f),
                    fontSize = 12.sp
                )
                Text(
                    text = exercise.equipment.displayName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = DesignSystem.Colors.TextSecondary
                )
            }
            
            Spacer(modifier = Modifier.height(6.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                // Difficulty Badge (Mock logic based on type or random for now as Exercise doesn't have difficulty field yet)
                // Assuming we want to display something. Let's use Type instead or add difficulty later.
                // For now, let's just show Type in a badge style.
                
                Badge(text = exercise.type.displayName, color = Color(0xFFfab005)) // Yellow for now
            }
        }
        
        if (isSelectionMode) {
             Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) DesignSystem.Colors.Primary else Color.Transparent)
                    .border(2.dp, if (isSelected) DesignSystem.Colors.Primary else DesignSystem.Colors.TextSecondary.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(DesignSystem.Colors.Primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = DesignSystem.Colors.Primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun Badge(text: String, color: Color) {
    Text(
        text = text,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.03f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = DesignSystem.Colors.TextSecondary,
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No se encontraron ejercicios",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = DesignSystem.Colors.TextSecondary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Intenta con otros filtros o términos de búsqueda",
            fontSize = 13.sp,
            color = DesignSystem.Colors.TextSecondary,
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

fun getMuscleGradient(muscle: MuscleGroup): List<Color> {
    return when (muscle) {
        MuscleGroup.CHEST, MuscleGroup.UPPER_CHEST, MuscleGroup.LOWER_CHEST -> listOf(Color(0xFFff6b6b), Color(0xFFee5a6f))
        MuscleGroup.LEGS, MuscleGroup.QUADS, MuscleGroup.HAMSTRINGS, MuscleGroup.GLUTES, MuscleGroup.CALVES -> listOf(Color(0xFF4c6ef5), Color(0xFF5f3dc4))
        MuscleGroup.BACK, MuscleGroup.LATS, MuscleGroup.UPPER_BACK, MuscleGroup.LOWER_BACK, MuscleGroup.TRAPS -> listOf(Color(0xFF20c997), Color(0xFF12b886))
        MuscleGroup.SHOULDERS, MuscleGroup.ANTERIOR_DELT, MuscleGroup.LATERAL_DELT, MuscleGroup.POSTERIOR_DELT -> listOf(Color(0xFFffd43b), Color(0xFFfab005))
        MuscleGroup.BICEPS, MuscleGroup.TRICEPS, MuscleGroup.FOREARMS -> listOf(Color(0xFF845ef7), Color(0xFF7048e8))
        MuscleGroup.ABS, MuscleGroup.OBLIQUES -> listOf(Color(0xFFff8787), Color(0xFFff6b6b))
        else -> listOf(Color(0xFFadb5bd), Color(0xFF868e96))
    }
}

