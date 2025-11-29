package com.schwarckstudio.lionfitness.ui.screens.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.schwarckstudio.lionfitness.ui.components.LocalTopBarState
import com.schwarckstudio.lionfitness.ui.components.TopBarVariant

@Composable
fun BodyScreen() {
    val topBarState = LocalTopBarState.current
    LaunchedEffect(Unit) {
        topBarState.update(
            variant = TopBarVariant.Body,
            title = "Cuerpo",
            onMenuClick = { /* TODO */ },
            onActionClick = { /* TODO */ }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F3)),
        contentAlignment = Alignment.Center
    ) {
        Text("Pantalla de Cuerpo (Placeholder)")
    }
}
