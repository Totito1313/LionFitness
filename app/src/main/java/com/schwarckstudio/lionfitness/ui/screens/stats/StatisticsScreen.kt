package com.schwarckstudio.lionfitness.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
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

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val profileState by profileViewModel.uiState.collectAsState()
    val workoutLogs by routineViewModel.workoutLogs.collectAsState()

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
            HeaderSection()
            
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

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Placeholder for consistency with other screens
        Spacer(modifier = Modifier.size(40.dp))

        Text(
            text = "Estadísticas",
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        // Placeholder for balance
        Spacer(modifier = Modifier.size(40.dp))
    }
}

@Composable
fun UserProfileCard(stats: UserStats, userName: String, userLevel: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .fillMaxWidth()
            .background(Color.White)
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(DesignSystem.Colors.Primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userName.take(2).uppercase(),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = userName,
                color = DesignSystem.Colors.TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "$userLevel • ${stats.totalWorkouts} entrenos",
                color = Color.Gray,
                fontSize = 14.sp,
            )
        }
        
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(DesignSystem.Colors.Primary)
                .padding(vertical = 6.dp, horizontal = 12.dp)
        ) {
            Text(
                text = "Racha: ${stats.streakDays} días",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
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
                .padding(bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatItem(
                title = "Calorías",
                value = "${stats.weeklyStats.totalCaloriesBurned}",
                subtitle = "Esta semana",
                iconColor = Color(0xFFFF5B5B),
                modifier = Modifier.weight(1f)
            )
            StatItem(
                title = "Entrenamientos",
                value = "${stats.totalWorkouts}",
                subtitle = "Total",
                iconColor = DesignSystem.Colors.Primary,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatItem(
                title = "Tiempo Total",
                value = String.format("%.1fh", stats.totalDurationHours),
                subtitle = "Total",
                iconColor = Color(0xFF10B981),
                modifier = Modifier.weight(1f)
            )
            StatItem(
                title = "Volumen",
                value = "${(stats.weeklyStats.totalVolume / 1000).toInt()}k",
                subtitle = "Kg esta semana",
                iconColor = Color(0xFFFFC107),
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .shadow(4.dp, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(iconColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(androidx.compose.material.icons.Icons.Filled.LocalFireDepartment, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = DesignSystem.Colors.TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            text = value,
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = subtitle,
            color = Color.Gray,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun CaloriesChartSection(stats: UserStats) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .fillMaxWidth()
            .background(Color.White)
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Calorías Quemadas",
                    color = DesignSystem.Colors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Últimos 7 días",
                    color = Color.Gray,
                    fontSize = 13.sp,
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE0E7FF))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("Semana", color = DesignSystem.Colors.Primary, fontSize = 12.sp)
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
                        lineColor = DesignSystem.Colors.Primary
                    )
                )
            ),
            model = chartEntryModel!!,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            modifier = Modifier
                .fillMaxWidth()
                .height(166.dp)
        )
        
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
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
