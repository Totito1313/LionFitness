import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.layout.*
import com.skydoves.landscapist.*
import com.skydoves.landscapist.coil3.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.geometry.Offset
@Composable
fun EstadisticasGraficosYProgreso() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight()
			.background(
				color = Color(0xFFFFFFFF),
			)
	){
		Column(
			modifier = Modifier
				.clip(shape = RoundedCornerShape(20.dp))
				.fillMaxWidth()
				.weight(1f)
				.background(
					color = Color(0xFFF1F1F3),
					shape = RoundedCornerShape(20.dp)
				)
				.shadow(
					elevation = 50.dp,
					spotColor = Color(0x40000000),
				)
				.padding(bottom = 1.dp,)
				.verticalScroll(rememberScrollState())
		){
			Row(
				modifier = Modifier
					.padding(bottom = 1.dp,)
					.fillMaxWidth()
					.padding(vertical = 12.dp,horizontal = 18.dp,)
			){
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(end = 8.dp,)
						.weight(1f)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/2dl17xes_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(end = 18.dp,)
							.width(50.dp)
							.height(50.dp)
					)
					Column(
					){
						Text("Estadísticas",
							color = Color(0xFF000000),
							fontSize = 20.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
				CoilImage(
					imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/o2x0cqtn_expires_30_days.png"},
					imageOptions = ImageOptions(contentScale = ContentScale.Crop),
					modifier = Modifier
						.width(100.dp)
						.height(50.dp)
				)
			}
			Column(
				modifier = Modifier
					.padding(horizontal = 20.dp,)
					.fillMaxWidth()
			){
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(bottom = 16.dp,)
						.clip(shape = RoundedCornerShape(28.dp))
						.fillMaxWidth()
						.background(
							color = Color(0x99FFFFFF),
							shape = RoundedCornerShape(28.dp)
						)
						.shadow(
							elevation = 20.dp,
							spotColor = Color(0x1A000000),
						)
						.padding(20.dp)
				){
					OutlinedButton(
						onClick = { println("Pressed!") },
						border = BorderStroke(0.dp, Color.Transparent),
						colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
						contentPadding = PaddingValues(),
						modifier = Modifier
							.padding(end = 16.dp,)
							.clip(shape = RoundedCornerShape(26843500.dp))
							.background(
								brush = Brush.linearGradient(
									colors = listOf(Color(0xFF4C6EF5), Color(0xFF845EF7), ),
									start = Offset.Zero,
									end = Offset(0F,Float.POSITIVE_INFINITY),
								)
							)
							.shadow(
								elevation = 6.dp,
								spotColor = Color(0x1A000000),
							)
					){
						Column(
							modifier = Modifier
								.padding(vertical = 14.dp,horizontal = 17.dp,)
						){
							Text("AZ",
								color = Color(0xFFFFFFFF),
								fontSize = 28.sp,
								fontWeight = FontWeight.Bold,
							)
						}
					}
					Column(
						modifier = Modifier
							.padding(end = 16.dp,)
					){
						Column(
							modifier = Modifier
								.padding(bottom = 4.dp,)
						){
							Text("Alex Zuñiga",
								color = Color(0xFF000000),
								fontSize = 18.sp,
								fontWeight = FontWeight.Bold,
							)
						}
						Column(
						){
							Text("Nivel Intermedio • 3 meses activo",
								color = Color(0xFF000000),
								fontSize = 14.sp,
							)
						}
					}
					OutlinedButton(
						onClick = { println("Pressed!") },
						border = BorderStroke(0.dp, Color.Transparent),
						colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
						contentPadding = PaddingValues(),
						modifier = Modifier
							.clip(shape = RoundedCornerShape(26843500.dp))
							.background(
								color = Color(0xFF4C6EF5),
								shape = RoundedCornerShape(26843500.dp)
							)
					){
						Column(
							modifier = Modifier
								.padding(vertical = 6.dp,horizontal = 12.dp,)
						){
							Text("Racha: 5 días",
								color = Color(0xFFFFFFFF),
								fontSize = 14.sp,
								fontWeight = FontWeight.Bold,
							)
						}
					}
				}
				Column(
					modifier = Modifier
						.padding(bottom = 16.dp,)
						.fillMaxWidth()
						.padding(end = 12.dp,)
				){
					Row(
						modifier = Modifier
							.padding(bottom = 12.dp,)
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 12.dp,)
								.clip(shape = RoundedCornerShape(20.dp))
								.weight(1f)
								.background(
									color = Color(0x99FFFFFF),
									shape = RoundedCornerShape(20.dp)
								)
								.shadow(
									elevation = 16.dp,
									spotColor = Color(0x12000000),
								)
								.padding(top = 16.dp,bottom = 16.dp,end = 16.dp,)
						){
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.padding(bottom = 7.dp,start = 16.dp,)
							){
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/au2akkfy_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 10.dp,)
										.width(36.dp)
										.height(36.dp)
								)
								Text("Calorías",
									color = Color(0xFF000000),
									fontSize = 13.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 8.dp,start = 16.dp,)
									.fillMaxWidth()
							){
								Text("3,280",
									color = Color(0xFF000000),
									fontSize = 24.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(start = 16.dp,)
									.fillMaxWidth()
							){
								Text("+15% esta semana",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
						Column(
							modifier = Modifier
								.clip(shape = RoundedCornerShape(20.dp))
								.weight(1f)
								.background(
									color = Color(0x99FFFFFF),
									shape = RoundedCornerShape(20.dp)
								)
								.shadow(
									elevation = 16.dp,
									spotColor = Color(0x12000000),
								)
								.padding(top = 16.dp,bottom = 16.dp,end = 16.dp,)
						){
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.padding(bottom = 7.dp,start = 16.dp,)
							){
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/cbbn8wwz_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 10.dp,)
										.width(36.dp)
										.height(36.dp)
								)
								Text("Entrenamientos",
									color = Color(0xFF000000),
									fontSize = 13.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 8.dp,start = 16.dp,)
									.fillMaxWidth()
							){
								Text("24",
									color = Color(0xFF000000),
									fontSize = 24.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(start = 16.dp,)
									.fillMaxWidth()
							){
								Text("Este mes",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
					}
					Row(
						modifier = Modifier
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 12.dp,)
								.clip(shape = RoundedCornerShape(20.dp))
								.weight(1f)
								.background(
									color = Color(0x99FFFFFF),
									shape = RoundedCornerShape(20.dp)
								)
								.shadow(
									elevation = 16.dp,
									spotColor = Color(0x12000000),
								)
								.padding(top = 16.dp,bottom = 16.dp,end = 16.dp,)
						){
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.padding(bottom = 7.dp,start = 16.dp,)
							){
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/18ixs83w_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 10.dp,)
										.width(36.dp)
										.height(36.dp)
								)
								Text("Tiempo Total",
									color = Color(0xFF000000),
									fontSize = 13.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 8.dp,start = 16.dp,)
									.fillMaxWidth()
							){
								Text("18.5h",
									color = Color(0xFF000000),
									fontSize = 24.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(start = 16.dp,)
									.fillMaxWidth()
							){
								Text("Este mes",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
						Column(
							modifier = Modifier
								.clip(shape = RoundedCornerShape(20.dp))
								.weight(1f)
								.background(
									color = Color(0x99FFFFFF),
									shape = RoundedCornerShape(20.dp)
								)
								.shadow(
									elevation = 16.dp,
									spotColor = Color(0x12000000),
								)
								.padding(top = 16.dp,bottom = 16.dp,end = 16.dp,)
						){
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.padding(bottom = 7.dp,start = 16.dp,)
							){
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/ctsqy7e9_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 10.dp,)
										.width(36.dp)
										.height(36.dp)
								)
								Text("Progreso",
									color = Color(0xFF000000),
									fontSize = 13.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 8.dp,start = 16.dp,)
									.fillMaxWidth()
							){
								Text("85%",
									color = Color(0xFF000000),
									fontSize = 24.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
								modifier = Modifier
									.padding(start = 16.dp,)
									.fillMaxWidth()
							){
								Text("Meta mensual",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
					}
				}
				Column(
					modifier = Modifier
						.padding(bottom = 16.dp,)
						.clip(shape = RoundedCornerShape(28.dp))
						.fillMaxWidth()
						.background(
							color = Color(0x99FFFFFF),
							shape = RoundedCornerShape(28.dp)
						)
						.shadow(
							elevation = 20.dp,
							spotColor = Color(0x1A000000),
						)
						.padding(20.dp)
				){
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier
							.padding(bottom = 16.dp,)
							.fillMaxWidth()
					){
						Column(
						){
							Column(
								modifier = Modifier
									.padding(bottom = 2.dp,)
							){
								Text("Calorías Quemadas",
									color = Color(0xFF000000),
									fontSize = 16.sp,
									fontWeight = FontWeight.Bold,
								)
							}
							Column(
							){
								Text("Últimos 7 días",
									color = Color(0xFF000000),
									fontSize = 13.sp,
								)
							}
						}
						Row(
							verticalAlignment = Alignment.CenterVertically,
							modifier = Modifier
								.clip(shape = RoundedCornerShape(12.dp))
								.background(
									color = Color(0x1A4C6EF5),
									shape = RoundedCornerShape(12.dp)
								)
								.padding(vertical = 6.dp,horizontal = 10.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/zzr81ywo_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(end = 5.dp,)
									.clip(shape = RoundedCornerShape(12.dp))
									.width(16.dp)
									.height(16.dp)
							)
							Column(
							){
								Text("Semana",
									color = Color(0xFF4C6EF5),
									fontSize = 12.sp,
									fontWeight = FontWeight.Bold,
								)
							}
						}
					}
					Column(
						modifier = Modifier
							.padding(top = 2.dp,bottom = 15.dp,start = 34.dp,end = 11.dp,)
							.fillMaxWidth()
					){
						Column(
						){
							Column(
								modifier = Modifier
									.padding(bottom = 21.dp,)
							){
								Text("600",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 25.dp,)
							){
								Text("450",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 25.dp,)
							){
								Text("300",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 25.dp,start = 2.dp,)
							){
								Text("150",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(start = 15.dp,)
							){
								Text("0",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
						CoilImage(
							imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/axdt5lbu_expires_30_days.png"},
							imageOptions = ImageOptions(contentScale = ContentScale.Crop),
							modifier = Modifier
								.padding(start = 25.dp,end = 4.dp,)
								.height(166.dp)
								.fillMaxWidth()
						)
						Row(
							modifier = Modifier
								.padding(start = 20.dp,)
								.fillMaxWidth()
						){
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.padding(end = 28.dp,)
									.weight(1f)
							){
								Text("Lun",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.padding(end = 27.dp,)
									.weight(1f)
							){
								Text("Mar",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.padding(end = 29.dp,)
									.weight(1f)
							){
								Text("Mié",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.padding(end = 29.dp,)
									.weight(1f)
							){
								Text("Jue",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.padding(end = 27.dp,)
									.weight(1f)
							){
								Text("Vie",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.padding(end = 16.dp,)
									.weight(1f)
							){
								Text("Sáb",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								horizontalAlignment = Alignment.CenterHorizontally,
								modifier = Modifier
									.weight(1f)
							){
								Text("Dom",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
					}
				}
				Column(
					modifier = Modifier
						.clip(shape = RoundedCornerShape(28.dp))
						.fillMaxWidth()
						.background(
							color = Color(0x99FFFFFF),
							shape = RoundedCornerShape(28.dp)
						)
						.shadow(
							elevation = 20.dp,
							spotColor = Color(0x1A000000),
						)
						.padding(horizontal = 20.dp,)
				){
					Column(
						modifier = Modifier
							.padding(top = 20.dp,bottom = 16.dp,)
							.fillMaxWidth()
					){
						Text("Actividad Reciente",
							color = Color(0xFF000000),
							fontSize = 16.sp,
							fontWeight = FontWeight.Bold,
						)
					}
					Box(
						modifier = Modifier
							.padding(bottom = 12.dp,)
					){
						Column(
							modifier = Modifier
								.fillMaxWidth()
						){
							Column(
								modifier = Modifier
									.clip(shape = RoundedCornerShape(16.dp))
									.height(68.dp)
									.fillMaxWidth()
									.background(
										color = Color(0x66FFFFFF),
										shape = RoundedCornerShape(16.dp)
									)
							){
							}
						}
						Column(
							modifier = Modifier
								.offset(x = -18.dp, y = 2.dp)
								.align(Alignment.TopStart)
								.padding(bottom = 2.dp)
								.clip(shape = RoundedCornerShape(79.dp))
								.fillMaxWidth()
								.background(
									color = Color(0x24FFFFFF),
									shape = RoundedCornerShape(79.dp)
								)
								.shadow(
									elevation = 16.dp,
									spotColor = Color(0x40000000),
								)
								.padding(vertical = 9.dp,)
						){
							Row(
								modifier = Modifier
									.padding(bottom = 2.dp,start = 18.dp,end = 18.dp,)
									.fillMaxWidth()
							){
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/zxeb3d7y_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 24.dp,)
										.height(24.dp)
										.weight(1f)
								)
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/b5n834sk_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 24.dp,)
										.height(24.dp)
										.weight(1f)
								)
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/kfweovjz_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 24.dp,)
										.height(24.dp)
										.weight(1f)
								)
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/830s0kug_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.padding(end = 24.dp,)
										.height(24.dp)
										.weight(1f)
								)
								CoilImage(
									imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/ok3pjvko_expires_30_days.png"},
									imageOptions = ImageOptions(contentScale = ContentScale.Crop),
									modifier = Modifier
										.height(24.dp)
										.weight(1f)
								)
							}
							Row(
								modifier = Modifier
									.padding(horizontal = 31.dp,)
									.fillMaxWidth()
							){
								Column(
									modifier = Modifier
										.padding(bottom = 1.dp,)
								){
									Text("Inicio",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
								}
								Column(
									modifier = Modifier
										.weight(1f)
								){
								}
								Text("Estadisticas",
									color = Color(0xFF000000),
									fontSize = 12.sp,
									modifier = Modifier
										.padding(end = 23.dp,)
								)
								Column(
									modifier = Modifier
										.padding(bottom = 1.dp,)
								){
									Text("Entrenos",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
								}
								Column(
									modifier = Modifier
										.weight(1f)
								){
								}
								Column(
									modifier = Modifier
										.padding(bottom = 1.dp,)
								){
									Text("Medidas",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
								}
								Column(
									modifier = Modifier
										.weight(1f)
								){
								}
								Text("Menu",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
					}
					Column(
						modifier = Modifier
							.clip(shape = RoundedCornerShape(16.dp))
							.height(2.dp)
							.fillMaxWidth()
							.background(
								color = Color(0x66FFFFFF),
								shape = RoundedCornerShape(16.dp)
							)
					){
					}
				}
			}
		}
	}
}