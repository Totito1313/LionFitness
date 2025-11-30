package com.schwarckstudio.lionfitness.ui.screens.profile

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit = {},
    onNavigateToEditProfile: () -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToPrivacy: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    // TopBar state managed by LionFitnessApp

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
            // Header text removed
            Spacer(modifier = Modifier.height(70.dp))

            ProfileHeader(
                username = uiState.user?.displayName ?: "Usuario",
                email = uiState.user?.email ?: "usuario@example.com",
                photoUrl = uiState.user?.photoUrl,
                weight = uiState.weight,
                height = uiState.height
            )

            Spacer(modifier = Modifier.height(24.dp))

            SettingsSection(
                title = "Entrenamientos",
                items = listOf(
                    SettingsItemData(
                        "Notificación de Récord Personal en Vivo",
                        Icons.Default.EmojiEvents,
                        hasSwitch = true,
                        isChecked = uiState.livePrNotificationsEnabled,
                        onCheckedChange = { viewModel.toggleLivePrNotifications() }
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            val context = androidx.compose.ui.platform.LocalContext.current
            
            SettingsSection(
                title = "Cuenta",
                items = listOf(
                    SettingsItemData("Editar Perfil", Icons.Default.Edit, onClick = onNavigateToEditProfile),
                    SettingsItemData(
                        "Notificaciones", 
                        Icons.Default.Notifications,
                        onClick = onNavigateToNotifications
                    ),
                    SettingsItemData(
                        "Privacidad y Seguridad", 
                        Icons.Default.Security,
                        onClick = onNavigateToPrivacy
                    ),
                    SettingsItemData(
                        "Tema Oscuro", 
                        Icons.Default.Person, // Placeholder icon
                        hasSwitch = true,
                        isChecked = uiState.isDarkTheme,
                        onCheckedChange = { viewModel.toggleTheme() }
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            SettingsSection(
                title = "Datos",
                items = listOf(
                    SettingsItemData(
                        "Exportar Datos",
                        Icons.Default.Share,
                        onClick = { viewModel.exportData() }
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            SettingsSection(
                title = "Sesión",
                items = listOf(
                    SettingsItemData(
                        "Cerrar Sesión", 
                        Icons.Default.Logout, 
                        isDestructive = true,
                        onClick = {
                            viewModel.signOut()
                            onNavigateToLogin()
                        }
                    )
                )
            )
        }
    }
}

@Composable
fun ProfileHeader(username: String, email: String, photoUrl: String?, weight: Double?, height: Double?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(DesignSystem.Colors.Primary),
            contentAlignment = Alignment.Center
        ) {
            if (photoUrl.isNullOrEmpty()) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            } else {
                com.skydoves.landscapist.coil3.CoilImage(
                    imageModel = { photoUrl },
                    modifier = Modifier.fillMaxSize(),
                    imageOptions = com.skydoves.landscapist.ImageOptions(
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = username,
            color = DesignSystem.Colors.TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = email,
            color = DesignSystem.Colors.TextSecondary,
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileStatItem("Peso", "${weight ?: "-"} kg")
            Spacer(modifier = Modifier.width(24.dp))
            Box(modifier = Modifier.height(24.dp).width(1.dp).background(Color.Gray.copy(alpha = 0.3f)))
            Spacer(modifier = Modifier.width(24.dp))
            ProfileStatItem("Altura", "${height ?: "-"} cm")
        }
    }
}

@Composable
fun ProfileStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DesignSystem.Colors.TextPrimary
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = DesignSystem.Colors.TextSecondary
        )
    }
}

data class SettingsItemData(
    val title: String,
    val icon: ImageVector,
    val isDestructive: Boolean = false,
    val hasSwitch: Boolean = false,
    val isChecked: Boolean = false,
    val onCheckedChange: (Boolean) -> Unit = {},
    val onClick: () -> Unit = {}
)

@Composable
fun SettingsSection(title: String, items: List<SettingsItemData>) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = title,
            color = DesignSystem.Colors.TextSecondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            items.forEachIndexed { index, item ->
                SettingsItem(item)
                if (index < items.size - 1) {
                    HorizontalDivider(
                        color = DesignSystem.Colors.Surface,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsItem(item: SettingsItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !item.hasSwitch, onClick = item.onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = if (item.isDestructive) Color.Red else DesignSystem.Colors.Primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            color = if (item.isDestructive) Color.Red else DesignSystem.Colors.TextPrimary,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        
        if (item.hasSwitch) {
            Switch(
                checked = item.isChecked,
                onCheckedChange = item.onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = DesignSystem.Colors.Primary
                )
            )
        } else {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = DesignSystem.Colors.TextSecondary
            )
        }
    }
}
