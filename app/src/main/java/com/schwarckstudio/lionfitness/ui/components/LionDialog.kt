package com.schwarckstudio.lionfitness.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@Composable
fun LionDialog(
    onDismissRequest: () -> Unit,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
    confirmButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    dismissButtonText: String? = null,
    onDismiss: (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = DesignSystem.Shapes.LargeCard,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (icon != null) {
                    Box(
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        icon()
                    }
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DesignSystem.Colors.TextPrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                content()

                if (confirmButtonText != null || dismissButtonText != null) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (dismissButtonText != null) {
                            TextButton(
                                onClick = { onDismiss?.invoke() ?: onDismissRequest() }
                            ) {
                                Text(
                                    text = dismissButtonText,
                                    color = DesignSystem.Colors.TextSecondary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        
                        if (confirmButtonText != null && dismissButtonText != null) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        if (confirmButtonText != null) {
                            Button(
                                onClick = { onConfirm?.invoke() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = DesignSystem.Colors.Primary,
                                    contentColor = Color.White
                                ),
                                shape = DesignSystem.Shapes.Button
                            ) {
                                Text(
                                    text = confirmButtonText,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
