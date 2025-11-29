package com.schwarckstudio.lionfitness.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schwarckstudio.lionfitness.ui.components.TopBar
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun SettingsScreen() {
    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    
    androidx.compose.runtime.LaunchedEffect(Unit) {
        topBarState.update(
            variant = TopBarVariant.Settings,
            title = "Configuraciones",
            isVisible = true,
            onMenuClick = { /* Handle menu click */ },
            onActionClick = { /* Handle action click */ }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings Screen Placeholder")
    }
}
