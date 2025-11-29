package com.schwarckstudio.lionfitness.ui.screens.news

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
fun NewsScreen() {
    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    
    // TopBar state managed by LionFitnessApp

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("News Screen Placeholder")
    }
}
