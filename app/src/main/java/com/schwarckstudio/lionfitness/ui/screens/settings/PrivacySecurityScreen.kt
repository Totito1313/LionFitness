package com.schwarckstudio.lionfitness.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacySecurityScreen(
    onNavigateBack: () -> Unit
) {
    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    androidx.compose.runtime.LaunchedEffect(Unit) {
        topBarState.update(
            variant = com.schwarckstudio.lionfitness.ui.components.TopBarVariant.Settings,
            title = "Privacidad y Seguridad",
            onBackClick = onNavigateBack
        )
    }

    Scaffold(
        // TopBar handled by LionFitnessApp
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                "Datos",
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
                    PrivacyItem(
                        title = "Exportar mis datos",
                        description = "Descarga una copia de tu historial y rutinas",
                        icon = Icons.Default.Download,
                        onClick = { /* Implement export logic */ }
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
                    PrivacyItem(
                        title = "Visibilidad del Perfil",
                        description = "Controla quién puede ver tu actividad",
                        icon = Icons.Default.Visibility,
                        onClick = { /* Implement visibility logic */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Zona de Peligro",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red.copy(alpha = 0.8f),
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    PrivacyItem(
                        title = "Eliminar Cuenta",
                        description = "Borrar permanentemente todos tus datos",
                        icon = Icons.Default.DeleteForever,
                        isDestructive = true,
                        onClick = { /* Implement delete account logic */ }
                    )
                }
            }
        }
    }
}

@Composable
fun PrivacyItem(
    title: String,
    description: String,
    icon: ImageVector,
    isDestructive: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isDestructive) Color.Red else DesignSystem.Colors.Primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = if (isDestructive) Color.Red else DesignSystem.Colors.TextPrimary
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = DesignSystem.Colors.TextSecondary
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = DesignSystem.Colors.TextSecondary
        )
    }
}
