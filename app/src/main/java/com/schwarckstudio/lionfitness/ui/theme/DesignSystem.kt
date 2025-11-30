package com.schwarckstudio.lionfitness.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object DesignSystem {
    object Colors {
        val Background = Color(0xFFF1F1F3)
        val Surface = Color(0xFFF1F1F3)
        val Primary = Color(0xFF4C6EF5)
        val Secondary = Color(0xFF845EF7)
        val TextPrimary = Color(0xFF000000)
        val TextSecondary = Color(0xFF848487)
        val AccentTeal = Color(0xFF66DBD3)
        val AccentTealLight = Color(0xFFA3E9E4)
    }

    object Shapes {
        val Card = RoundedCornerShape(20.dp)
        val Button = RoundedCornerShape(12.dp)
        val LargeCard = RoundedCornerShape(28.dp)
        val Input = RoundedCornerShape(12.dp)
    }
}
