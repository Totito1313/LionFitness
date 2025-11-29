package com.schwarckstudio.lionfitness.navigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color de fondo opaco: rgb(252,252,255)
val CornerMenuBg = Color(0xFFFCFCFF)

@Composable
fun CornerMenu(
    modifier: Modifier = Modifier,
    onMenuItemClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .width(220.dp) // Ancho ajustado para verse bien en móvil
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(28.dp),
                spotColor = Color(0x66000000)
            )
            .background(CornerMenuBg, RoundedCornerShape(28.dp))
            .padding(vertical = 30.dp, horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        CornerMenuItem(
            label = "Perfil",
            iconData = SvgPaths.PROFILE_VECTOR,
            onClick = { onMenuItemClick("Perfil") }
        )

        CornerMenuItem(
            label = "Comunidad",
            iconData = SvgPaths.COMMUNITY_VECTOR,
            onClick = { onMenuItemClick("Comunidad") }
        )

        CornerMenuItem(
            label = "Ejercicios",
            iconList = SvgPaths.ENERGY_SCORE_PARTS,
            onClick = { onMenuItemClick("Ejercicios") }
        )

        // Divisor punteado
        DashedDivider()

        CornerMenuItem(
            label = "Noticias",
            iconData = SvgPaths.NEWS_VECTOR,
            onClick = { onMenuItemClick("Noticias") }
        )

        CornerMenuItem(
            label = "Configuraciones",
            iconData = SvgPaths.SETTINGS_VECTOR,
            onClick = { onMenuItemClick("Configuraciones") }
        )
    }
}

@Composable
fun CornerMenuItem(
    label: String,
    iconData: String? = null,
    iconList: List<String>? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
            .padding(vertical = 4.dp, horizontal = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(modifier = Modifier.size(24.dp)) {
            if (iconData != null) {
                // Icono simple (1 path)
                DrawSvgPath(pathData = iconData, color = Color.Black, viewportWidth = 24f, viewportHeight = 24f)
            } else if (iconList != null) {
                // Icono compuesto (Energy Score)
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // Escalado estándar para 24x24 (similar a DrawSvgPath pero para lista)
                    val viewportWidth = 20f // El energy score original parece ser base 20 o 19
                    val viewportHeight = 20f
                    val scaleX = size.width / viewportWidth
                    val scaleY = size.height / viewportHeight
                    val finalScale = minOf(scaleX, scaleY)
                    val translateX = (size.width - (viewportWidth * finalScale)) / 2f
                    val translateY = (size.height - (viewportHeight * finalScale)) / 2f

                    scale(finalScale, pivot = Offset.Zero) {
                        translate(translateX / finalScale, translateY / finalScale) {
                            iconList.forEach { pathStr ->
                                drawPath(SvgPaths.parse(pathStr), Color.Black)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = label,
            fontSize = 18.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun DashedDivider() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        drawLine(
            color = Color.Black.copy(alpha = 0.6f),
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
    }
}