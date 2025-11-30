package com.schwarckstudio.lionfitness.ui.screens.stats

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import com.schwarckstudio.lionfitness.core.data.repository.UserStats
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.screens.profile.ProfileViewModel
import com.schwarckstudio.lionfitness.ui.components.HistoryCard
import com.schwarckstudio.lionfitness.ui.screens.routines.RoutineViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant
import androidx.compose.material3.Scaffold

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val profileState by profileViewModel.uiState.collectAsState()
    val workoutLogs by routineViewModel.workoutLogs.collectAsState()



    // TopBar state managed by LionFitnessApp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
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
            // HeaderSection removed
            Spacer(modifier = Modifier.height(70.dp))
            
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                uiState.userStats?.let { stats ->
                    UserProfileCard(
                        stats = stats,
                        userName = profileState.user?.displayName ?: "Usuario",
                        userLevel = "Nivel Intermedio" // Placeholder logic for level
                    )
                    StatsGrid(stats)
                    CaloriesChartSection(stats)
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    text = "Actividad Reciente",
                    color = DesignSystem.Colors.TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (workoutLogs.isEmpty()) {
                    Text("No hay actividad reciente", color = Color.Gray)
                } else {
                    workoutLogs.take(5).forEach { log ->
                        HistoryCard(
                            log = log,
                            onDelete = { routineViewModel.deleteWorkout(log.id) },
                            onEdit = { /* Navigate to details if needed */ },
                            onShare = { /* Share logic */ }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

// HeaderSection removed

@Composable
fun UserProfileCard(stats: UserStats, userName: String, userLevel: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 24.dp)
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
        // Avatar
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFF6366F1)), // Purple-ish blue
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AZ", // Hardcoded for now as per image
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Alex Zuñiga", // Hardcoded for now
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nivel Intermedio • 3\nmeses activo",
                fontSize = 13.sp,
                color = Color.Gray,
                lineHeight = 18.sp
            )
        }

        // Streak Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF4F46E5)) // Darker blue
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Racha: 5 días",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun StatsGrid(stats: UserStats) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatItem(
                title = "Calorías",
                value = "3,280",
                subtitle = "+15% esta semana",
                iconColor = Color(0xFFFF5B5B),
                icon = Icons.Default.LocalFireDepartment,
                modifier = Modifier.weight(1f)
            )
            StatItem(
                title = "Entrenamientos",
                value = "24",
                subtitle = "Este mes",
                iconColor = Color(0xFF7C3AED),
                icon = Icons.Default.Menu, // Placeholder icon
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatItem(
                title = "Tiempo Total",
                value = "18.5h",
                subtitle = "Este mes",
                iconColor = Color(0xFF10B981),
                icon = Icons.Default.Menu, // Placeholder icon
                modifier = Modifier.weight(1f)
            )
            StatItem(
                title = "Progreso",
                value = "85%",
                subtitle = "Meta mensual",
                iconColor = Color(0xFFFFC107),
                icon = Icons.Default.Menu, // Placeholder icon
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatItem(
    title: String,
    value: String,
    subtitle: String,
    iconColor: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = Color(0x1A000000),
                ambientColor = Color(0x1A000000)
            )
            .clip(shape = RoundedCornerShape(24.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            text = value,
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = subtitle,
            color = Color.Gray,
            fontSize = 13.sp,
        )
    }
}

@Composable
fun CaloriesChartSection(stats: UserStats) {
    Column(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(32.dp),
                spotColor = Color(0x1A000000),
                ambientColor = Color(0x1A000000)
            )
            .clip(shape = RoundedCornerShape(32.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Calorías Quemadas",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Últimos 7 días",
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0E7FF))
                    .clickable { /* TODO */ }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color(0xFF4F46E5),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Semana", color = Color(0xFF4F46E5), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        
        // Prepare chart data
        val now = LocalDate.now()
        val days = (0..6).map { now.minusDays(it.toLong()) }.reversed()
        val entries = days.mapIndexed { index, date ->
            entryOf(index.toFloat(), stats.weeklyStats.workoutsPerDay[date]?.toFloat() ?: 0f)
        }
        val chartEntryModel = ChartEntryModelProducer(listOf(entries)).getModel()

        Chart(
            chart = lineChart(
                lines = listOf(
                    com.patrykandpatrick.vico.compose.chart.line.lineSpec(
                        lineColor = Color(0xFF4F46E5),
                        lineThickness = 3.dp
                    )
                )
            ),
            model = chartEntryModel!!,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            days.forEach { date ->
                Text(
                    text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()).take(3),
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
