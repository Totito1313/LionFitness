package com.schwarckstudio.lionfitness.ui.screens.measurements

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.schwarckstudio.lionfitness.ui.components.LionDialog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun BodyMeasurementsScreen(
    viewModel: BodyMeasurementsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToBody: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()


    
    var showAddMeasurementDialog by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
    
    if (showAddMeasurementDialog) {
        AddMeasurementDialog(
            onDismiss = { showAddMeasurementDialog = false },
            onConfirm = { weight, chest, waist, arms, legs, hips ->
                viewModel.addMeasurement(weight, chest, waist, arms, legs, hips)
                showAddMeasurementDialog = false
            }
        )
    }

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    // TopBar state managed by LionFitnessApp

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F1F3))
        ) {
            // Top App Bar removed
            // Top App Bar removed


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 70.dp, bottom = 100.dp)
            ) {
                item {
                    LastUpdateCard(
                        lastUpdate = uiState.lastUpdate,
                        onUpdateClick = { showAddMeasurementDialog = true }
                    )
                }

                item {
                    MeasurementsGrid(
                        measurements = uiState.measurements,
                        onAddClick = { showAddMeasurementDialog = true }
                    )
                }

                item {
                    WeightProgressChart(weightHistory = uiState.weightHistory)
                }

                item {
                    MeasurementHistory(history = uiState.history)
                }
            }
        }

        FloatingActionButton(
            onClick = { showAddMeasurementDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(bottom = 80.dp)
                .size(60.dp),
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF4c6ef5), Color(0xFF5f3dc4))
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.size(28.dp))
            }
        }
    }
}


@Composable
fun AddMeasurementDialog(
    onDismiss: () -> Unit,
    onConfirm: (Double?, Double?, Double?, Double?, Double?, Double?) -> Unit
) {
    var weightText by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    var chestText by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    var waistText by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    var armsText by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    var legsText by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }
    var hipsText by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf("") }

    LionDialog(
        onDismissRequest = onDismiss,
        title = "Registrar Medidas",
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MeasurementInputField(label = "Peso (kg)", value = weightText, onValueChange = { weightText = it })
                MeasurementInputField(label = "Pecho (cm)", value = chestText, onValueChange = { chestText = it })
                MeasurementInputField(label = "Cintura (cm)", value = waistText, onValueChange = { waistText = it })
                MeasurementInputField(label = "Brazos (cm)", value = armsText, onValueChange = { armsText = it })
                MeasurementInputField(label = "Piernas (cm)", value = legsText, onValueChange = { legsText = it })
                MeasurementInputField(label = "Caderas (cm)", value = hipsText, onValueChange = { hipsText = it })
            }
        },
        confirmButtonText = "Guardar",
        onConfirm = {
            onConfirm(
                weightText.toDoubleOrNull(),
                chestText.toDoubleOrNull(),
                waistText.toDoubleOrNull(),
                armsText.toDoubleOrNull(),
                legsText.toDoubleOrNull(),
                hipsText.toDoubleOrNull()
            )
        },
        dismissButtonText = "Cancelar",
        onDismiss = onDismiss
    )
}

@Composable
fun MeasurementInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp) },
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF4c6ef5),
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color(0xFF4c6ef5),
            cursorColor = Color(0xFF4c6ef5),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}

@Composable
fun LastUpdateCard(lastUpdate: String, onUpdateClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0x1A4C6EF5)) // 0.1 alpha
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4c6ef5))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Última actualización: $lastUpdate",
                fontSize = 13.sp,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
        Text(
            text = "Actualizar",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4c6ef5),
            modifier = Modifier.clickable { onUpdateClick() }
        )
    }
}

@Composable
fun MeasurementsGrid(measurements: List<BodyMeasurement>, onAddClick: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Medidas Actuales",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Button(
                onClick = onAddClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4c6ef5)),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Añadir", fontSize = 13.sp)
            }
        }

        // Using a Column with Rows instead of LazyVerticalGrid inside LazyColumn to avoid nesting scrollables
        // Assuming fixed 2 columns
        measurements.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    MeasurementCard(
                        measurement = item,
                        modifier = Modifier.weight(1f).padding(bottom = 12.dp)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun MeasurementCard(measurement: BodyMeasurement, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(measurement.colorStart), Color(measurement.colorEnd))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Straighten, // Placeholder icon
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            val trendColor = if (measurement.isPositive) Color(0xFF12b886) else Color(0xFFff6b6b)
            val trendBg = if (measurement.isPositive) Color(0x2620c997) else Color(0x26ff6b6b)
            
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(trendBg)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (measurement.isPositive) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                    contentDescription = null,
                    tint = trendColor,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${kotlin.math.abs(measurement.change)}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = trendColor
                )
            }
        }
        
        Text(
            text = measurement.name,
            fontSize = 13.sp,
            color = Color.Black.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = measurement.value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = measurement.unit,
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Composable
fun WeightProgressChart(weightHistory: List<WeightDataPoint>) {
    Column(
        modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Progreso de Peso",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Últimos 5 meses",
                    fontSize = 13.sp,
                    color = Color.Black.copy(alpha = 0.6f)
                )
            }
            
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0x26ff6b6b))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.TrendingDown,
                    contentDescription = null,
                    tint = Color(0xFFff6b6b),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "-4 kg",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFff6b6b)
                )
            }
        }
        
        val chartEntryModel = entryModelOf(*weightHistory.map { it.weight }.toTypedArray())
        
        Chart(
            chart = lineChart(
                lines = listOf(
                    com.patrykandpatrick.vico.compose.chart.line.lineSpec(
                        lineColor = Color(0xFF4c6ef5)
                    )
                )
            ),
            model = chartEntryModel,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            modifier = Modifier.height(180.dp)
        )
    }
}

@Composable
fun MeasurementHistory(history: List<MeasurementHistoryItem>) {
    Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Historial",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                modifier = Modifier.clickable { /* View all */ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ver todo",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4c6ef5)
                )
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF4c6ef5),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        
        history.forEach { item ->
            HistoryItemCard(item)
        }
    }
}

@Composable
fun HistoryItemCard(item: MeasurementHistoryItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White.copy(alpha = 0.5f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF4c6ef5), Color(0xFF5f3dc4))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(14.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.date,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = item.summary,
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.6f)
            )
        }
        
        IconButton(
            onClick = { /* Edit action */ },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.Black.copy(alpha = 0.6f),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
