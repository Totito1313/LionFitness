package com.schwarckstudio.lionfitness.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onViewHistoryClick: () -> Unit = {},
    onViewRoutinesClick: () -> Unit = {},
    onRoutineClick: (String) -> Unit = {},
    onCreateRoutineClick: () -> Unit = {},
    onCopyRoutine: (String) -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // TopBar state managed by LionFitnessApp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DesignSystem.Colors.Background)
        ){
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = DesignSystem.Colors.Surface,
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .verticalScroll(rememberScrollState())
            ){
                // Header removed, replaced by TopBar
                Spacer(modifier = Modifier.height(20.dp))

            // Heatmap Section
            Column(
                modifier = Modifier
                    .padding(bottom = 17.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ){
                Text("Frecuencia y Esfuerzo",
                    color = DesignSystem.Colors.TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp, start = 25.dp)
                )
                HeatmapCalendar(workoutDays = uiState.workoutDays)
            }

            // Quick Routines Section
            Column(
                modifier = Modifier
                    .padding(bottom = 17.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ){
                Text("Rutinas rápidas",
                    color = DesignSystem.Colors.TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp, start = 24.dp)
                )
                
                if (uiState.routines.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(28.dp))
                            .fillMaxWidth()
                            .background(
                                color = Color(0x80FCFCFF),
                                shape = RoundedCornerShape(28.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    ) {
                        // Display first 3 routines in a row if available
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            uiState.routines.take(3).forEach { routine ->
                                QuickRoutineItem(
                                    title = routine.name,
                                    iconUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/heu87eix_expires_30_days.png", // Placeholder icon
                                    modifier = Modifier.weight(1f),
                                    onClick = { onRoutineClick(routine.id) }
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))

                        // Display next 2 routines and a "View All" button
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            uiState.routines.drop(3).take(2).forEach { routine ->
                                QuickRoutineItem(
                                    title = routine.name,
                                    iconUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/ae07ab0c_expires_30_days.png", // Placeholder icon
                                    modifier = Modifier.weight(1f),
                                    onClick = { onRoutineClick(routine.id) }
                                )
                            }
                            
                            // View All Button
                            Column(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(23.dp))
                                    .weight(1f)
                                    .background(
                                        color = DesignSystem.Colors.Surface,
                                        shape = RoundedCornerShape(23.dp)
                                    )
                                    .clickable { onViewRoutinesClick() }
                                    .padding(vertical = 12.dp, horizontal = 13.dp)
                            ){
                                CoilImage(
                                    imageModel = { "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/lryu763m_expires_30_days.png" },
                                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                    modifier = Modifier
                                        .padding(bottom = 14.dp)
                                        .clip(shape = RoundedCornerShape(23.dp))
                                        .size(25.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ){
                                    Text("Rutinas",
                                        color = DesignSystem.Colors.TextPrimary,
                                        fontSize = 12.sp,
                                    )
                                    Text("${uiState.routines.size}",
                                        color = DesignSystem.Colors.TextPrimary,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "No hay rutinas disponibles", 
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Button(
                            onClick = onCreateRoutineClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DesignSystem.Colors.Primary
                            ),
                            shape = DesignSystem.Shapes.Button
                        ) {
                            Text("Crear Rutina")
                        }
                    }
                }
            }

            // History Section
            Column(
                modifier = Modifier
                    .padding(bottom = 1.dp, start = 11.dp, end = 11.dp)
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ){
                Text("Historial de Entrenamientos",
                    color = DesignSystem.Colors.TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 14.dp, start = 16.dp)
                )
                
                if (uiState.recentWorkouts.isNotEmpty()) {
                    uiState.recentWorkouts.forEach { workout ->
                        val dateStr = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.getDefault())
                            .format(workout.startTime.toDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate())
                        
                        HistoryItem(
                            title = "${workout.name} - $dateStr",
                            iconUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/9o32ivxz_expires_30_days.png", // Placeholder
                            onClick = onViewHistoryClick
                        )
                    }
                } else {
                     Text("No hay historial reciente", modifier = Modifier.padding(start = 16.dp), color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun HeatmapCalendar(workoutDays: List<LocalDate>) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(28.dp))
            .fillMaxWidth()
            .background(
                color = Color(0x80FCFCFF),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(vertical = 7.dp)
    ){
        // Simplified Calendar Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 2.dp, start = 12.dp, end = 12.dp)
                .fillMaxWidth()
        ){
            Text(LocalDate.now().month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault()).replaceFirstChar { it.uppercase() },
                color = DesignSystem.Colors.TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        
        // Simplified Grid for Demo (Ideally use a proper Calendar library or logic)
        // For now, just showing a static grid but coloring based on workoutDays if match
        // This is a placeholder for a real dynamic calendar
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp, start = 23.dp, end = 23.dp)
                .fillMaxWidth()
        ){
             // Days of week
            Column(modifier = Modifier.padding(end = 24.dp)) {
                listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                    Text(day, color = DesignSystem.Colors.TextPrimary, fontSize = 12.sp, modifier = Modifier.padding(vertical = 3.dp))
                }
            }
            
            // Grid
             Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ){
                val today = LocalDate.now()
                val currentMonth = today.month
                // Just generating some dummy days for visual structure, 
                // in a real app this needs proper calendar logic
                val days = (1..30).toList()
                
                days.chunked(7).forEach { week ->
                    Column(modifier = Modifier.padding(end = 6.dp)) {
                        week.forEach { day ->
                            val date = LocalDate.of(today.year, currentMonth, day)
                            val isWorkoutDay = workoutDays.contains(date)
                            val color = if (isWorkoutDay) DesignSystem.Colors.AccentTeal else Color(0xFFE8F8F7)
                            
                            DayButton(day.toString(), color, false)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayButton(text: String, color: Color, isShortBottom: Boolean) {
    Box(
        modifier = Modifier
            .padding(bottom = if(isShortBottom) 6.dp else 7.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(color)
            .size(20.dp), // Fixed size for uniformity
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color(0xFF1D2838), fontSize = 10.sp)
    }
}

@Composable
fun QuickRoutineItem(title: String, iconUrl: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(23.dp))
            .background(
                color = DesignSystem.Colors.Surface,
                shape = RoundedCornerShape(23.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ){
        CoilImage(
            imageModel = { iconUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(bottom = 15.dp, start = 12.dp)
                .clip(shape = RoundedCornerShape(23.dp))
                .size(25.dp)
        )
        Text(title,
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 12.dp),
            maxLines = 1
        )
    }
}

@Composable
fun HistoryItem(
    title: String, 
    iconUrl: String, 
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 10.dp)
            .clip(shape = RoundedCornerShape(54.dp))
            .fillMaxWidth()
            .background(
                color = Color(0xFFFCFCFF),
                shape = RoundedCornerShape(54.dp)
            )
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ){
        CoilImage(
            imageModel = { iconUrl },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 10.dp)
                .clip(shape = RoundedCornerShape(54.dp))
                .size(40.dp)
        )
        
        Text(
            text = title,
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    color = DesignSystem.Colors.TextPrimary,
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable { onClick() }
                .padding(vertical = 6.dp, horizontal = 20.dp)
        ){
            Text("Detalles",
                color = Color(0xFFFFFFFF),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
