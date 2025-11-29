package com.schwarckstudio.lionfitness.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.schwarckstudio.lionfitness.R

// Enum con todas las variantes
enum class TopBarVariant {
    Home, Stats, Training, ActiveTraining, FinishTraining, Exercises,
    TrainingSummary, EditTraining, BodyMeasurements, UserProfile,
    Routines, RoutineDetails, EditRoutine, News, Community, Body, Settings
}

@Composable
fun TopBar(
    state: TopBarState,
    modifier: Modifier = Modifier
) {
    if (!state.isVisible) return

    var showMenu by remember { mutableStateOf(false) }

    // Contenedor principal con statusBarsPadding para evitar solapamiento
    Box(modifier = modifier.fillMaxWidth().statusBarsPadding()) {
        when (state.variant) {
            // --- GRUPO 1: EDICIÓN ---
            TopBarVariant.EditRoutine -> EditHeader(
                title = state.title.ifEmpty { "Editar Rutina" },
                onBackClick = state.onBackClick,
                onActionClick = state.onActionClick
            )
            TopBarVariant.EditTraining -> EditHeader(
                title = state.title.ifEmpty { "Editar Entrenamiento" },
                onBackClick = state.onBackClick,
                onActionClick = state.onActionClick
            )
            TopBarVariant.FinishTraining -> EditHeader(
                title = "Finalizar Entrenamiento",
                onBackClick = state.onBackClick,
                onActionClick = state.onActionClick
            )

            // --- GRUPO 2: DETALLES ---
            TopBarVariant.RoutineDetails -> DetailHeader(
                title = state.title.ifEmpty { "Rutinas" },
                subtitle = state.subtitle ?: "Detalles",
                onBackClick = state.onBackClick,
                onMenuClick = { showMenu = true }
            )
            TopBarVariant.Training -> DetailHeader(
                title = state.title.ifEmpty { "Entrenamientos" },
                subtitle = state.subtitle ?: "Detalles",
                onBackClick = state.onBackClick,
                onMenuClick = { showMenu = true }
            )

            // --- GRUPO 3: LISTAS Y ESTADÍSTICAS (Píldora Unificada) ---
            TopBarVariant.Routines -> StatsStyleHeader(
                title = state.title.ifEmpty { "Entrenamientos" },
                hasSearch = true,
                hasMore = true,
                onMenuClick = state.onMenuClick,
                onSearchClick = state.onActionClick,
                onMoreClick = { showMenu = true }
            )
            TopBarVariant.Exercises -> StatsStyleHeader(
                title = state.title.ifEmpty { "Ejercicios" },
                hasSearch = true,
                hasMore = true,
                hasMenu = false, // Hide menu
                onMenuClick = state.onMenuClick,
                onSearchClick = state.onActionClick,
                onMoreClick = { showMenu = true }
            )
            TopBarVariant.Settings -> StatsStyleHeader(
                title = state.title.ifEmpty { "Configuraciones" },
                hasSearch = true,
                hasMore = true,
                hasMenu = false, // Hide menu
                onMenuClick = state.onMenuClick,
                onSearchClick = state.onActionClick,
                onMoreClick = { showMenu = true }
            )
            TopBarVariant.Community -> StatsStyleHeader(
                title = state.title.ifEmpty { "Comunidad" },
                hasSearch = true,
                hasMore = true,
                onMenuClick = state.onMenuClick,
                onSearchClick = state.onActionClick,
                onMoreClick = { showMenu = true }
            )
            TopBarVariant.Stats -> StatsStyleHeader(
                title = state.title.ifEmpty { "Estadísticas" },
                hasSearch = true,
                hasMore = true,
                onMenuClick = state.onMenuClick,
                onSearchClick = state.onActionClick,
                onMoreClick = { showMenu = true }
            )
            TopBarVariant.TrainingSummary -> StatsStyleHeader(
                title = state.title.ifEmpty { "Entrenamientos" },
                hasSearch = true,
                hasMore = true,
                onMenuClick = state.onMenuClick,
                onSearchClick = state.onActionClick,
                onMoreClick = { showMenu = true }
            )
            // Estos solo tienen "More", no Search, así que no usan la píldora doble
            TopBarVariant.Body, TopBarVariant.BodyMeasurements -> StatsStyleHeader(
                title = state.title.ifEmpty { "Medidas Corporales" },
                hasSearch = false,
                hasMore = true,
                onMenuClick = state.onMenuClick,
                onMoreClick = state.onActionClick // Redirect to action (Body screen)
            )

            // --- GRUPO 4: PANTALLAS PRINCIPALES ---
            TopBarVariant.News -> GradientHeader(
                title = state.title.ifEmpty { "Noticias" },
                showUserSubtitle = false,
                rightIcon = R.drawable.ic_more_vertical,
                onMenuClick = state.onMenuClick,
                onActionClick = { showMenu = true }
            )
            TopBarVariant.UserProfile -> GradientHeader(
                title = state.title.ifEmpty { "Usuario" },
                showUserSubtitle = false,
                rightIcon = R.drawable.ic_more_vertical,
                onMenuClick = state.onMenuClick,
                onActionClick = { showMenu = true }
            )
            TopBarVariant.Home -> GradientHeader(
                title = "Inicio",
                userName = state.userName,
                showUserSubtitle = true,
                rightIcon = state.rightIcon ?: R.drawable.ic_more_vertical, // Fallback
                onMenuClick = state.onMenuClick,
                onActionClick = state.onActionClick
            )

            // --- GRUPO 5: ENTRENAMIENTO ACTIVO ---
            TopBarVariant.ActiveTraining -> ActiveTrainingHeader(
                duration = state.duration,
                heartRate = state.heartRate,
                onBackClick = state.onBackClick,
                onActionClick = state.onActionClick,
                onMenuClick = { showMenu = true }
            )
        }

        // Dropdown Menu Logic
        if (state.menuItems.isNotEmpty()) {
            Box(modifier = Modifier.align(Alignment.TopEnd).padding(top = 50.dp, end = 16.dp)) {
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    containerColor = Color.White
                ) {
                    state.menuItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.text, color = item.color) },
                            onClick = {
                                showMenu = false
                                item.onClick()
                            }
                        )
                    }
                }
            }
        }
    }
}

// =====================================================================
// SUB-COMPONENTES (Layouts)
// =====================================================================

@Composable
fun StandardHeaderContainer(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // AJUSTAR ALTURA AQUÍ (Antes 60.dp)
            .padding(horizontal = 18.dp, vertical = 4.dp), // AJUSTAR PADDING AQUÍ
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Píldora Unificada: Contenedor compartido para Lupa + 3 Puntos
 */
@Composable
fun CombinedPill(
    onSearchClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .width(100.dp)
            .advancedShadow(
                color = Color.Black,
                alpha = 0.25f,
                cornersRadius = 25.dp,
                shadowBlurRadius = 25.dp
            )
            .clip(RoundedCornerShape(25.dp))
            .background(Color(0xFFF1F1F3).copy(alpha = 0.7f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Contenedor Izquierdo (Buscar)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { onSearchClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        }
        
        // Contenedor Derecho (Más)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { onMoreClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_vertical),
                    contentDescription = "Más",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun EditHeader(title: String, onBackClick: () -> Unit = {}, onActionClick: () -> Unit = {}) {
    StandardHeaderContainer {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            GlassButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Atrás",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            TitleContainer(title)
        }
        GlassButton(onClick = onActionClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Guardar",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun DetailHeader(title: String, subtitle: String, onBackClick: () -> Unit = {}, onMenuClick: () -> Unit = {}) {
    StandardHeaderContainer {
        Row(modifier = Modifier.weight(1f)) {
            TitleContainer(title = title, subtitle = subtitle)
        }
        GlassButton(onClick = onMenuClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vertical),
                contentDescription = "Opciones",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun StatsStyleHeader(
    title: String,
    hasSearch: Boolean,
    hasMore: Boolean,
    hasMenu: Boolean = true,
    onMenuClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onMoreClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // AJUSTAR ALTURA AQUÍ (Antes 85.dp)
    ) {
        // Fondo con gradiente restaurado
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        0.0f to Color(0xFFF1F1F3),
                        0.55f to Color(0xFFF1F1F3),
                        1.0f to Color(0xFFF1F1F3).copy(alpha = 0f)
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 4.dp) // AJUSTAR PADDING AQUÍ
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Izquierda
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                if (hasMenu) {
                    GlassButton(onClick = onMenuClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menú",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
                // Título flotante sin contenedor blanco
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1
                )
            }

            // Derecha: Lógica de Píldora Unificada
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (hasSearch && hasMore) {
                    // Píldora Combinada
                    CombinedPill(
                        onSearchClick = onSearchClick,
                        onMoreClick = onMoreClick
                    )
                } else {
                    // Botones Individuales
                    if (hasSearch) {
                        GlassButton(onClick = onSearchClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Buscar",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                    }
                    if (hasMore) {
                        GlassButton(onClick = onMoreClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_more_vertical),
                                contentDescription = "Más",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradientHeader(
    title: String,
    userName: String? = null,
    showUserSubtitle: Boolean,
    rightIcon: Int,
    onActionClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // AJUSTAR ALTURA AQUÍ (Antes 62.dp)
    ) {
        // Fondo con gradiente restaurado
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        0.0f to Color(0xFFF1F1F3),
                        0.55f to Color(0xFFF1F1F3),
                        1.0f to Color(0xFFF1F1F3).copy(alpha = 0f)
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 4.dp), // AJUSTAR PADDING AQUÍ
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                TitleContainer(title = title, subtitle = if (showUserSubtitle) "Feliz entrenamiento $userName" else null)
            }

            GlassButton(onClick = onActionClick) {
                Icon(
                    painter = painterResource(id = rightIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun ActiveTrainingHeader(
    duration: String,
    heartRate: String,
    onBackClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF1F1F3)) // Solid background
            .border(width = 1.dp, color = Color.Black.copy(alpha = 0.05f)) // border-b border-black/5
            .padding(top = 4.dp, start = 18.dp, end = 18.dp, bottom = 4.dp) // AJUSTAR PADDING AQUÍ (Antes 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                GlassButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Atrás",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                TitleContainer("Entrenando")
            }
            GlassButton(onClick = onActionClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Listo",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                StatusChip(text = "En curso", dotColor = Color(0xFF12B886))
                DeviceChip(text = "Galaxy Watch")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                MetricChip(icon = R.drawable.ic_timer, text = duration, color = Color.Black)
                MetricChip(icon = R.drawable.ic_heart, text = "$heartRate bpm", color = Color(0xFFFF6B6B), bgColor = Color(0x1AFF6B6B))
            }
        }
    }
}

@Composable
fun GlassButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.95f else 1f, label = "scale")

    Box(
        modifier = modifier
            .scale(scale)
            .size(50.dp)
            .advancedShadow(
                color = Color.Black,
                alpha = 0.25f,
                cornersRadius = 25.dp,
                shadowBlurRadius = 25.dp
            )
            .clip(CircleShape)
            .background(Color(0xFFF1F1F3).copy(alpha = 0.7f)) // Increased alpha for visibility
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

/**
 * Título limpio sin fondo ni sombras.
 */
@Composable
fun TitleContainer(title: String, subtitle: String? = null) {
    Column(
        modifier = Modifier.padding(start = 8.dp), // Solo un poco de padding izquierdo
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 20.sp
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF848487),
                lineHeight = 14.sp
            )
        }
    }
}

// --- Chips Pequeños ---

@Composable
fun StatusChip(text: String, dotColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(dotColor, shape = RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, fontSize = 13.sp, color = Color.Black.copy(alpha = 0.6f))
    }
}

@Composable
fun DeviceChip(text: String) {
    Row(
        modifier = Modifier
            .background(Color(0xFF4C6EF5).copy(alpha = 0.08f), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_watch),
            contentDescription = null,
            tint = Color(0xFF4C6EF5),
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, fontSize = 11.sp, color = Color(0xFF4C6EF5), fontWeight = FontWeight.Medium)
    }
}

@Composable
fun MetricChip(icon: Int, text: String, color: Color, bgColor: Color = Color.Black.copy(alpha = 0.04f)) {
    Row(
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = color)
    }
}

// --- Extension for Custom Shadow ---
fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 0.2f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {
    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = androidx.compose.ui.graphics.Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}