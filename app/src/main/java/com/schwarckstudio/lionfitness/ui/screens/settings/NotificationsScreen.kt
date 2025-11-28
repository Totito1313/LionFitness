package com.schwarckstudio.lionfitness.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onNavigateBack: () -> Unit
) {
    var pushEnabled by remember { mutableStateOf(true) }
    var emailEnabled by remember { mutableStateOf(false) }
    var workoutReminders by remember { mutableStateOf(true) }
    var prAlerts by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DesignSystem.Colors.Background
                )
            )
        },
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    NotificationSwitchItem(
                        title = "Notificaciones Push",
                        description = "Recibe alertas en tu dispositivo",
                        isChecked = pushEnabled,
                        onCheckedChange = { pushEnabled = it }
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    NotificationSwitchItem(
                        title = "Correos Electrónicos",
                        description = "Recibe resúmenes semanales y novedades",
                        isChecked = emailEnabled,
                        onCheckedChange = { emailEnabled = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                "Tipos de Notificación",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DesignSystem.Colors.TextSecondary,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    NotificationSwitchItem(
                        title = "Recordatorios de Entrenamiento",
                        description = "Te avisaremos si llevas días sin entrenar",
                        isChecked = workoutReminders,
                        onCheckedChange = { workoutReminders = it }
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    NotificationSwitchItem(
                        title = "Alertas de Récords (PR)",
                        description = "Celebra tus nuevos logros al instante",
                        isChecked = prAlerts,
                        onCheckedChange = { prAlerts = it }
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationSwitchItem(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = DesignSystem.Colors.TextPrimary)
            Text(description, fontSize = 12.sp, color = DesignSystem.Colors.TextSecondary)
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = DesignSystem.Colors.Primary
            )
        )
    }
}
