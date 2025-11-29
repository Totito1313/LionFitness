package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant
import java.util.Locale

@Composable
fun MyRoutinesScreen(
    viewModel: RoutineViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onRoutineClick: (String) -> Unit,
    onStartRoutine: (String) -> Unit
) {
    val routines by viewModel.routines.collectAsState()
    
    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    LaunchedEffect(Unit) {
        topBarState.update(
            variant = TopBarVariant.Routines,
            onMenuClick = { /* TODO */ },
            onActionClick = { /* TODO */ }
        )
    }

    // Main Container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF1F1F3))
        ) {
        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Header Section removed
            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Card
            StatsCard(routinesCount = routines.size)

            Spacer(modifier = Modifier.height(24.dp))

            // Tabs
            TabsSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Routines List
            routines.forEachIndexed { index, routine ->
                // Mocking colors and data for visual variety based on index
                val cardColor = if (index % 2 == 0) Color(0xFF5B4DFF) else Color(0xFFFF5B5B)
                val tags = if (index % 2 == 0) listOf("Cuádriceps", "Isquiotibiales", "Glúteos") else listOf("Pecho", "Espalda", "Core")
                val description = if (index % 2 == 0) "Rutina completa de piernas con énfasis en volumen" else "Supersets para pecho y espalda, máximo pump"
                val difficulty = if (index % 2 == 0) "Avanzado" else "Intermedio"
                val lastUsed = if (index % 2 == 0) "Hace 2 días" else "Hace 5 días"

                RoutineCard(
                    routine = routine,
                    cardColor = cardColor,
                    tags = tags,
                    description = description,
                    difficulty = difficulty,
                    lastUsed = lastUsed,
                    onClick = { onRoutineClick(routine.id) },
                    onStart = { onStartRoutine(routine.id) }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            Spacer(modifier = Modifier.height(80.dp)) // Bottom padding
        }
    }
}

// HeaderSection removed

@Composable
fun StatsCard(routinesCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(32.dp),
                spotColor = Color(0x1A000000),
                ambientColor = Color(0x1A000000)
            )
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Esta semana",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$routinesCount rutinas",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Tiempo total",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "6.5 horas",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TabsSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFE0E7FF)) // Light blue
                .clickable { /* TODO */ }
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Mis Rutinas",
                color = Color(0xFF4F46E5),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Explorar",
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.clickable { /* TODO */ }
        )
    }
}

@Composable
fun RoutineCard(
    routine: Routine,
    cardColor: Color,
    tags: List<String>,
    description: String,
    difficulty: String,
    lastUsed: String,
    onClick: () -> Unit,
    onStart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(32.dp),
                spotColor = Color(0x1A000000),
                ambientColor = Color(0x1A000000)
            )
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardColor)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = routine.name,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorite",
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.White.copy(alpha = 0.2f), CircleShape)
                        .padding(6.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = description,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Stats Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoutineStat(
                icon = Icons.Outlined.AdsClick, // Target icon approximation
                value = "${routine.exercises.size}",
                label = "Ejercicios"
            )
            RoutineStat(
                icon = Icons.Outlined.Schedule,
                value = "75", // Mock duration
                label = "Duración"
            )
            
            // Difficulty Badge
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Outlined.TrendingUp,
                    contentDescription = null,
                    tint = Color(0xFFFF5B5B),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFF5B5B))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = difficulty,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        // Footer
        Column(
            modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 24.dp)
        ) {
            Text(
                text = "Último uso: $lastUsed",
                color = Color.Gray,
                fontSize = 13.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onStart,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4F46E5)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Iniciar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                
                FilledIconButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier.size(50.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFFF3F4F6)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Outlined.ContentCopy, contentDescription = "Copy", tint = Color.Black)
                }
                
                FilledIconButton(
                    onClick = onClick,
                    modifier = Modifier.size(50.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFFF3F4F6)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.ChevronRight, contentDescription = "Details", tint = Color.Black)
                }
            }
        }
    }
}

@Composable
fun RoutineStat(
    icon: ImageVector,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF9FAFB))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .width(80.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF4F46E5),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}
