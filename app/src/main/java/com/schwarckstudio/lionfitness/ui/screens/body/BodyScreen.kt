package com.schwarckstudio.lionfitness.ui.screens.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            .background(Color(0xFFF1F1F3))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Box(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Pantalla de Cuerpo (Placeholder)")
            }
        }
    }
}
