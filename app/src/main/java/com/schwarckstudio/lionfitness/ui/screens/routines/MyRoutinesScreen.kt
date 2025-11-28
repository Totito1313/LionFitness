package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import java.util.Locale

@Composable
fun MyRoutinesScreen(
    viewModel: RoutineViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onRoutineClick: (String) -> Unit,
    onStartRoutine: (String) -> Unit
) {
    val routines by viewModel.routines.collectAsState()
    val userStats by viewModel.userStats.collectAsState()
    var selectedTab by remember { mutableStateOf(0) } // 0: Mis Rutinas, 1: Explorar

    Scaffold(
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rutinas",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = DesignSystem.Colors.TextPrimary
                )
                
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /* Search */ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = DesignSystem.Colors.TextPrimary, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { /* Menu */ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = DesignSystem.Colors.TextPrimary, modifier = Modifier.size(20.dp))
                    }
                }
            }

            // Summary Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(24.dp), spotColor = Color(0x10000000)),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Esta semana",
                            fontSize = 12.sp,
                            color = DesignSystem.Colors.TextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${userStats.weeklyStats.totalWorkouts} rutinas",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = DesignSystem.Colors.TextPrimary
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Tiempo total",
                            fontSize = 12.sp,
                            color = DesignSystem.Colors.TextSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = String.format(Locale.US, "%.1f horas", userStats.weeklyStats.totalDurationMinutes / 60.0),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = DesignSystem.Colors.TextPrimary
                        )
                    }
                }
            }

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                TabButton(
                    text = "Mis Rutinas",
                    isSelected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                Spacer(modifier = Modifier.width(16.dp))
                TabButton(
                    text = "Explorar",
                    isSelected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }

            // Routine List
            if (selectedTab == 0) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(routines) { routine ->
                        RoutineCard(
                            routine = routine,
                            onStart = { onStartRoutine(routine.id) },
                            onDetail = { onRoutineClick(routine.id) }
                        )
                    }
                }
            } else {
                // Explore Placeholder
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Explorar próximamente", color = DesignSystem.Colors.TextSecondary)
                }
            }
        }
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) DesignSystem.Colors.Primary.copy(alpha = 0.15f) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) DesignSystem.Colors.Primary else DesignSystem.Colors.TextSecondary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}

@Composable
fun RoutineCard(
    routine: Routine,
    onStart: () -> Unit,
    onDetail: () -> Unit
) {
    // Mock data for UI if missing
    val tags = if (routine.tags.isNotEmpty()) routine.tags else listOf("Fuerza", "Hipertrofia")
    val difficulty = if (routine.difficulty.isNotEmpty()) routine.difficulty else "Intermedio"
    val color = if (routine.color != 0L) Color(routine.color) else listOf(Color(0xFF5B4DFF), Color(0xFFFF4444), Color(0xFF10B981), Color(0xFFFFC107)).random()
    val lastUsed = "Hace 2 días" // Mock for now

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(24.dp), spotColor = Color(0x10000000))
            .clickable(onClick = onDetail),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color)
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = routine.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (routine.isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = routine.description.ifEmpty { "Rutina completa para todo el cuerpo" },
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tags.take(3).forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.2f))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = tag,
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            // Body
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RoutineStat(
                        icon = Icons.Default.FitnessCenter,
                        value = "${routine.exercises.size}",
                        label = "Ejercicios",
                        color = Color(0xFF5B4DFF)
                    )
                    RoutineStat(
                        icon = Icons.Default.Schedule,
                        value = "60", // Mock duration
                        label = "Duración",
                        color = Color(0xFF10B981)
                    )
                    
                    // Difficulty Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                when(difficulty) {
                                    "Avanzado" -> Color(0xFFFF4444).copy(alpha = 0.1f)
                                    "Intermedio" -> Color(0xFFFFC107).copy(alpha = 0.1f)
                                    else -> Color(0xFF10B981).copy(alpha = 0.1f)
                                }
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.TrendingUp,
                                contentDescription = null,
                                tint = when(difficulty) {
                                    "Avanzado" -> Color(0xFFFF4444)
                                    "Intermedio" -> Color(0xFFFFC107)
                                    else -> Color(0xFF10B981)
                                },
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = difficulty,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = when(difficulty) {
                                    "Avanzado" -> Color(0xFFFF4444)
                                    "Intermedio" -> Color(0xFFFFC107)
                                    else -> Color(0xFF10B981)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Último uso: $lastUsed",
                    fontSize = 12.sp,
                    color = DesignSystem.Colors.TextSecondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onStart,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DesignSystem.Colors.Primary
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Iniciar", fontWeight = FontWeight.Bold)
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    FilledIconButton(
                        onClick = { /* Copy */ },
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = DesignSystem.Colors.Surface
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Outlined.ContentCopy, contentDescription = "Copy", tint = DesignSystem.Colors.TextPrimary)
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    FilledIconButton(
                        onClick = onDetail,
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = DesignSystem.Colors.Surface
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.ChevronRight, contentDescription = "Detail", tint = DesignSystem.Colors.TextPrimary)
                    }
                }
            }
        }
    }
}

@Composable
fun RoutineStat(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DesignSystem.Colors.TextPrimary
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = DesignSystem.Colors.TextSecondary
        )
    }
}
