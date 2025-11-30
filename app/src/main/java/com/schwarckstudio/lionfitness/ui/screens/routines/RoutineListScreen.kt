package com.schwarckstudio.lionfitness.ui.screens.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.core.model.Routine
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.HistoryCard
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun RoutineListScreen(
    viewModel: RoutineViewModel = hiltViewModel(),
    onCreateRoutineClick: () -> Unit,
    onRoutineClick: (Routine) -> Unit,
    onWorkoutClick: (String) -> Unit,
    onNavigateToMyRoutines: () -> Unit
) {
    val routines by viewModel.routines.collectAsState()
    val workoutLogs by viewModel.workoutLogs.collectAsState()
    val globalRecords by viewModel.globalRecords.collectAsState()
    var showMenu by remember { mutableStateOf(false) }
    var showShareDialog by remember { mutableStateOf(false) }
    var workoutToShare by remember { mutableStateOf<com.schwarckstudio.lionfitness.core.model.WorkoutLog?>(null) }

    // TopBar state managed by LionFitnessApp

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DesignSystem.Colors.Background)
        ) {
            Column(
                modifier = Modifier
                    .clip(shape = DesignSystem.Shapes.Card)
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = DesignSystem.Colors.Surface,
                        shape = DesignSystem.Shapes.Card
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                // Header removed
                Spacer(modifier = Modifier.height(70.dp))

                FeaturedSection(records = globalRecords)

                // History Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Historial",
                            color = DesignSystem.Colors.TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        val context = LocalContext.current
                        TextButton(onClick = { 
                             android.widget.Toast.makeText(context, "Historial completo próximamente", android.widget.Toast.LENGTH_SHORT).show()
                        }) {
                            Text("Ver todo", color = DesignSystem.Colors.Primary)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    if (workoutLogs.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(DesignSystem.Colors.Background),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.History,
                                    contentDescription = null,
                                    tint = DesignSystem.Colors.TextSecondary,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "No hay historial de entrenamientos",
                                color = DesignSystem.Colors.TextSecondary,
                                fontSize = 14.sp
                            )
                        }
                    } else {
                        workoutLogs.forEach { log ->
                            HistoryCard(
                                log = log,
                                onDelete = { viewModel.deleteWorkout(log.id) },
                                onEdit = { onWorkoutClick(log.id) },
                                onShare = { 
                                    workoutToShare = log
                                    showShareDialog = true
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

        FloatingActionButton(
            onClick = onCreateRoutineClick,
            containerColor = DesignSystem.Colors.Primary,
            contentColor = Color.White,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp).padding(bottom = 80.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Create Routine")
        }
    }
}

@Composable
fun FeaturedSection(records: com.schwarckstudio.lionfitness.core.data.repository.GlobalRecords) {
    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        ) {
            Text(
                text = "Destacados",
                color = DesignSystem.Colors.TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.Gray, modifier = Modifier.rotate(90f))
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                FeaturedCard(
                    title = "Mayor Volumen",
                    subtitle = "Volumen Máximo",
                    value = "${records.maxVolume.toInt()} kg",
                    subValue = "En una sesión",
                    iconUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/fsyvmvdc_expires_30_days.png",
                    color = Color(0xFFFFC107)
                )
            }
            item {
                val hours = records.maxDurationSeconds / 3600
                val minutes = (records.maxDurationSeconds % 3600) / 60
                FeaturedCard(
                    title = "Mayor Duración",
                    subtitle = "Tiempo Récord",
                    value = "${hours}h ${minutes}m",
                    subValue = "Sesión más larga",
                    iconUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/trtcewv7_expires_30_days.png",
                    color = Color(0xFF5B4DFF)
                )
            }
            item {
                FeaturedCard(
                    title = "Peso Máximo",
                    subtitle = "Fuerza Bruta",
                    value = "${records.maxWeight} kg",
                    subValue = "1RM Global",
                    iconUrl = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/fsyvmvdc_expires_30_days.png",
                    color = Color(0xFFFF4444)
                )
            }
        }
    }
}

@Composable
fun FeaturedCard(
    title: String,
    subtitle: String,
    value: String,
    subValue: String,
    iconUrl: String,
    color: Color
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(24.dp))
            .background(Color.White)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x1A000000),
            )
            .padding(18.dp)
            .width(200.dp)
    ) {
        Row(
            modifier = Modifier.padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
             Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                 // Icon placeholder
                 Icon(androidx.compose.material.icons.Icons.Filled.EmojiEvents, contentDescription = null, tint = Color.White)
            }
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF3F4F6))
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Text(
            text = subtitle,
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
         Text(
            text = subValue,
            color = Color.Gray,
            fontSize = 12.sp,
        )
    }
}

fun Modifier.rotate(degrees: Float) = this.then(Modifier.graphicsLayer(rotationZ = degrees))
