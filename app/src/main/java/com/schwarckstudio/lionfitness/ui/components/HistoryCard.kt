package com.schwarckstudio.lionfitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@Composable
fun HistoryCard(
    log: WorkoutLog,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onShare: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = DesignSystem.Shapes.Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // User Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(DesignSystem.Colors.Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text("U", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(log.name.ifEmpty { "Entrenamiento" }, fontWeight = FontWeight.Bold, color = DesignSystem.Colors.TextPrimary)
                    val duration = if (log.endTime != null) {
                        val diff = log.endTime!!.seconds - log.startTime.seconds
                        val hours = diff / 3600
                        val minutes = (diff % 3600) / 60
                        if (hours > 0) "${hours}h ${minutes}min" else "${minutes}min"
                    } else {
                        "En progreso"
                    }
                    Text("hace un momento • $duration", color = DesignSystem.Colors.TextSecondary, fontSize = 12.sp)
                }
                Box {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.Gray)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        containerColor = Color.White
                    ) {
                        DropdownMenuItem(
                            text = { Text("Compartir") },
                            onClick = {
                                showMenu = false
                                onShare()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Editar") },
                            onClick = {
                                showMenu = false
                                onEdit()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Eliminar", color = Color.Red) },
                            onClick = {
                                showMenu = false
                                onDelete()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(log.name.ifEmpty { "Entrenamiento" }, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DesignSystem.Colors.TextPrimary)
            Spacer(modifier = Modifier.height(16.dp))

            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val duration = if (log.endTime != null) {
                    val diff = log.endTime!!.seconds - log.startTime.seconds
                    val hours = diff / 3600
                    val minutes = (diff % 3600) / 60
                    if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
                } else "-"
                
                HistoryStat("Tiempo", duration)
                HistoryStat("Volumen", "${log.totalVolume.toInt()} kg")
                HistoryStat("Récords", "🏆 ${log.records}")
                HistoryStat("Calorías", "🔥 ${log.calories.toInt()}")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Exercises
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                log.exercises.take(3).forEach { exercise ->
                    val sets = exercise.sets.filter { it.completed }
                    val setInfo = if (sets.isNotEmpty()) {
                        val bestSet = sets.maxByOrNull { it.weight }
                        "${sets.size} series • Mejor: ${bestSet?.weight}kg x ${bestSet?.reps}"
                    } else {
                        "${exercise.sets.size} series"
                    }
                    ExerciseHistoryItem(exercise.exerciseName, setInfo)
                }
            }

            if (log.exercises.size > 3) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Ver ${log.exercises.size - 3} más ejercicios",
                    color = DesignSystem.Colors.Primary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            // Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Me gusta", color = Color.Gray)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { }
                ) {
                    Icon(Icons.Default.Comment, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Comentar", color = Color.Gray)
                }
                IconButton(onClick = onShare) {
                    Icon(Icons.Default.Share, contentDescription = null, tint = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun HistoryStat(label: String, value: String) {
    Column(
        modifier = Modifier
            .background(Color(0xFFF3F4F6), RoundedCornerShape(8.dp))
            .padding(8.dp)
            .width(70.dp)
    ) {
        Text(label, fontSize = 10.sp, color = Color.Gray)
        Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DesignSystem.Colors.TextPrimary)
    }
}

@Composable
fun ExerciseHistoryItem(name: String, details: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF9FAFB), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFE0E7FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = DesignSystem.Colors.Primary, modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DesignSystem.Colors.TextPrimary)
            Text(details, fontSize = 12.sp, color = DesignSystem.Colors.TextSecondary)
        }
    }
}
