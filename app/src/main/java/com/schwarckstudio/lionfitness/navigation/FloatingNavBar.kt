package com.schwarckstudio.lionfitness.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- COLORES ---
val NavBackground = Color(0xFFFFFFFF)
val ActiveItemBg = Color(0xFFF1F1F3)
val InactiveColor = Color(0xFF676767)

@Composable
fun FloatingNavBar(
    currentRoute: String?,
    isMenuOpen: Boolean,
    onNavigate: (String) -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Determine active tab based on currentRoute
    val activeTab = when (currentRoute) {
        Screen.Home.route -> 0
        Screen.Statistics.route -> 1
        Screen.Routines.route -> 2
        Screen.BodyMeasurements.route -> 3
        else -> -1 // No active tab or handled elsewhere
    }

    Box(
        modifier = modifier
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(79.dp),
                spotColor = Color(0x40000000)
            )
            .background(NavBackground, RoundedCornerShape(79.dp))
            .padding(horizontal = 6.dp, vertical = 5.dp)
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                label = "Inicio",
                isActive = activeTab == 0,
                onClick = { onNavigate(Screen.Home.route) }
            ) { color ->
                // HOME: Viewport original 13x15
                DrawSvgPath(
                    pathData = SvgPaths.HOME_VECTOR,
                    color = color,
                    style = "fill_stroke",
                    viewportWidth = 13f,
                    viewportHeight = 15f
                )
            }

            NavItem(
                label = "Estadísticas",
                isActive = activeTab == 1,
                onClick = { onNavigate(Screen.Statistics.route) }
            ) { color ->
                // STATS: Viewport estándar 20x20
                DrawSvgPath(SvgPaths.STATS_VECTOR, color, style = "stroke")
            }

            NavItem(
                label = "Entrenos",
                isActive = activeTab == 2,
                onClick = { onNavigate(Screen.Routines.route) }
            ) { color ->
                // WORKOUT: Viewport estándar 20x20
                DrawSvgPath(SvgPaths.WORKOUT_VECTOR, color, style = "fill_even_odd")
            }

            NavItem(
                label = "Medidas",
                isActive = activeTab == 3,
                onClick = { onNavigate(Screen.BodyMeasurements.route) }
            ) { color ->
                // MEASURES: Viewport estándar 20x20
                Box(Modifier.fillMaxSize()) {
                    DrawSvgPath(SvgPaths.MEASURE_FILL, color, style = "fill_even_odd")
                    DrawSvgPath(SvgPaths.MEASURE_STROKE, color, style = "stroke")
                }
            }

            NavItem(
                label = "Menu",
                isActive = isMenuOpen,
                onClick = { onMenuClick() }
            ) { color ->
                // MENU: Viewport estándar 20x20
                Box(Modifier.fillMaxSize()) {
                    DrawSvgPath(SvgPaths.MENU_FILL, color, style = "fill")
                    DrawSvgPath(SvgPaths.MENU_STROKE, color, style = "stroke")
                }
            }
        }
    }
}

@Composable
fun NavItem(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    iconContent: @Composable (Color) -> Unit
) {
    val contentColor by animateColorAsState(
        targetValue = if (isActive) Color.Black else InactiveColor,
        animationSpec = tween(200), label = "color"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) ActiveItemBg else Color.Transparent,
        animationSpec = tween(200), label = "bg"
    )

    Column(
        modifier = Modifier
            .width(75.dp)
            .clip(RoundedCornerShape(79.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(vertical = 6.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tamaño icono 24dp
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            iconContent(contentColor)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = contentColor,
            fontSize = 11.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
fun DrawSvgPath(
    pathData: String,
    color: Color,
    style: String = "fill",
    strokeWidth: Float = 1.5f,
    viewportWidth: Float = 20f,
    viewportHeight: Float = 20f
) {
    val path = remember(pathData) { SvgPaths.parse(pathData) }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleX = size.width / viewportWidth
        val scaleY = size.height / viewportHeight

        // Mantener aspecto "Fit"
        val scale = minOf(scaleX, scaleY)

        // Calcular traslación para centrar
        val translateX = (size.width - (viewportWidth * scale)) / 2f
        val translateY = (size.height - (viewportHeight * scale)) / 2f

        // Aplicamos transformaciones
        scale(scale = scale, pivot = androidx.compose.ui.geometry.Offset.Zero) {
            translate(left = translateX / scale, top = translateY / scale) {
                when (style) {
                    "fill" -> {
                        drawPath(path, color)
                    }
                    "fill_even_odd" -> {
                        path.fillType = PathFillType.EvenOdd
                        drawPath(path, color)
                    }
                    "stroke" -> {
                        drawPath(
                            path,
                            color,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }
                    "fill_stroke" -> {
                        drawPath(path, color)
                        drawPath(
                            path,
                            color,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFloatingNav() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F3)),
        contentAlignment = Alignment.Center
    ) {
        FloatingNavBar(
            currentRoute = Screen.Home.route,
            isMenuOpen = false,
            onNavigate = {},
            onMenuClick = {}
        )
    }
}