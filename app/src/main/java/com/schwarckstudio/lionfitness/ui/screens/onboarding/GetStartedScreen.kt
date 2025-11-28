package com.schwarckstudio.lionfitness.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun GetStartedScreen(
    onGetStartedClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 12.dp, start = 33.dp, end = 33.dp)
                    .fillMaxWidth()
            ) {
                CoilImage(
                    imageModel = { "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/8yymbdny_expires_30_days.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .clip(shape = DesignSystem.Shapes.Card)
                        .width(40.dp)
                        .height(27.dp)
                )
                CoilImage(
                    imageModel = { "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/laqc75pv_expires_30_days.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .clip(shape = DesignSystem.Shapes.Card)
                        .width(123.dp)
                        .height(27.dp)
                )
            }
            Box {
                CoilImage(
                    imageModel = { "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/jmif8eqd_expires_30_days.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(bottom = 59.dp)
                        .clip(shape = DesignSystem.Shapes.Card)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .padding(bottom = 59.dp)
                        .clip(shape = DesignSystem.Shapes.Card)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Entrena, registra tu progreso y supera tus límites con nosotros.",
                        color = DesignSystem.Colors.TextPrimary,
                        fontSize = 63.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 60.sp,
                        modifier = Modifier
                            .padding(top = 204.dp, bottom = 93.dp, start = 53.dp)
                            .width(314.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 55.dp, start = 43.dp, end = 43.dp)
                            .clip(shape = RoundedCornerShape(153.dp))
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFF010102),
                                shape = RoundedCornerShape(153.dp)
                            )
                            .clickable { onGetStartedClick() }
                            .padding(vertical = 14.dp, horizontal = 20.dp)
                    ) {
                        Text(
                            text = "¡Empieza ahora tu cambio!",
                            color = Color(0xFFFFFFFF),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        CoilImage(
                            imageModel = { "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/g5o5u0wn_expires_30_days.png" },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(153.dp))
                                .width(24.dp)
                                .height(24.dp)
                        )
                    }
                }
            }
        }
    }
}
