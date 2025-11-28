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
import androidx.compose.ui.text.*
import androidx.compose.ui.layout.*
import com.skydoves.landscapist.*
import com.skydoves.landscapist.coil3.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.geometry.Offset
@Composable
fun Inicio() {
	val textField1 = remember { mutableStateOf("") }
	val textField2 = remember { mutableStateOf("") }
	val textField3 = remember { mutableStateOf("") }
	val textField4 = remember { mutableStateOf("") }
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
				.verticalScroll(rememberScrollState())
		){
			Box(
				modifier = Modifier
					.padding(bottom = 17.dp,)
			){
				Column(
					modifier = Modifier
						.fillMaxWidth()
				){
					Column(
						modifier = Modifier
							.padding(bottom = 24.dp,)
							.fillMaxWidth()
							.background(
								brush = Brush.linearGradient(
									colors = listOf(Color(0x00F1F1F3), Color(0xFFF1F1F3), ),
									start = Offset.Zero,
									end = Offset(0F,Float.POSITIVE_INFINITY),
								)
							)
							.padding(top = 13.dp,bottom = 13.dp,start = 26.dp,)
					){
						Text("Inicio",
							color = Color(0xFF000000),
							fontSize = 20.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
				CoilImage(
					imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/x3q924ji_expires_30_days.png"},
					imageOptions = ImageOptions(contentScale = ContentScale.Crop),
					modifier = Modifier
						.offset(x = -18.dp, y = 14.dp)
						.align(Alignment.TopEnd)
						.padding(bottom = 14.dp)
						.width(50.dp)
						.height(50.dp)
				)
				Text("Feliz entrenamiento Alan",
					color = Color(0xFF848487),
					fontSize = 14.sp,
					modifier = Modifier
						.offset(x = 26.dp, y = -16.dp)
						.align(Alignment.BottomStart)
						.padding(start = 26.dp)
				)
			}
			Column(
				modifier = Modifier
					.padding(bottom = 17.dp,start = 12.dp,end = 12.dp,)
					.fillMaxWidth()
					.padding(top = 4.dp,)
			){
				Text("Frecuencia y Esfuerzo",
					color = Color(0xFF000000),
					fontSize = 20.sp,
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.padding(bottom = 8.dp,start = 25.dp,)
				)
				Column(
					modifier = Modifier
						.clip(shape = RoundedCornerShape(28.dp))
						.fillMaxWidth()
						.background(
							color = Color(0x80FCFCFF),
							shape = RoundedCornerShape(28.dp)
						)
						.padding(vertical = 7.dp,)
				){
					Row(
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier
							.padding(bottom = 2.dp,start = 12.dp,end = 12.dp,)
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 4.dp,)
								.width(58.dp)
								.height(22.dp)
						){
						}
						Column(
							modifier = Modifier
								.padding(end = 4.dp,)
						){
							Text("May",
								color = Color(0xFF000000),
								fontSize = 14.sp,
								fontWeight = FontWeight.Bold,
							)
						}
						Text("June",
							color = Color(0xFF000000),
							fontSize = 14.sp,
							fontWeight = FontWeight.Bold,
						)
					}
					Row(
						modifier = Modifier
							.padding(bottom = 2.dp,start = 23.dp,end = 23.dp,)
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 24.dp,)
						){
							Column(
								modifier = Modifier
									.padding(bottom = 6.dp,)
									.padding(vertical = 3.dp,)
							){
								Text("Mon",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 7.dp,)
									.padding(vertical = 3.dp,)
							){
								Text("Tue",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 7.dp,)
									.padding(vertical = 3.dp,)
							){
								Text("Wed",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 7.dp,)
									.padding(vertical = 3.dp,)
							){
								Text("Thu",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 6.dp,)
									.padding(vertical = 3.dp,)
							){
								Text("Fri",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(bottom = 7.dp,)
									.padding(vertical = 3.dp,)
							){
								Text("Sat",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
							Column(
								modifier = Modifier
									.padding(vertical = 3.dp,)
							){
								Text("Sun",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
							}
						}
						Row(
							horizontalArrangement = Arrangement.Center,
							modifier = Modifier
								.padding(end = 24.dp,)
								.weight(1f)
						){
							Column(
								modifier = Modifier
									.padding(end = 6.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 4.dp,horizontal = 8.dp,)
									){
										Text("1",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("2",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("3",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("4",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("5",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("6",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("7",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.padding(end = 7.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("8",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("9",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("10",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("11",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("12",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("13",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("14",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.padding(end = 7.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("15",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("16",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("17",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("18",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(5.dp)
									){
										Text("19",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("20",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("21",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.padding(end = 6.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("22",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("23",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 5.dp,)
									){
										Text("24",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("25",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("26",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("27",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("28",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("29",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("30",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("31",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								Column(
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								Column(
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								Column(
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								Column(
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
							}
						}
						Row(
							horizontalArrangement = Arrangement.Center,
							modifier = Modifier
								.padding(end = 12.dp,)
								.weight(1f)
						){
							Column(
								modifier = Modifier
									.padding(end = 7.dp,)
							){
								Column(
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								Column(
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								Column(
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 10.dp,)
									){
										Text("1",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("2",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("3",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("4",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.padding(end = 6.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("5",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("6",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 9.dp,)
									){
										Text("7",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("8",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 8.dp,)
									){
										Text("9",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("10",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("11",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.padding(end = 7.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("12",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("13",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("14",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("15",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("16",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("17",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("18",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.padding(end = 7.dp,)
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("19",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFE8F8F7),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("20",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 7.dp,)
									){
										Text("21",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("22",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF33D1C6),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("23",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF66DBD3),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("24",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("25",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
							}
							Column(
							){
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 5.dp,)
									){
										Text("26",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("27",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("28",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFF00D3C2),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 5.dp,)
									){
										Text("29",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								OutlinedButton(
									onClick = { println("Pressed!") },
									border = BorderStroke(0.dp, Color.Transparent),
									colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
									contentPadding = PaddingValues(),
									modifier = Modifier
										.padding(bottom = 6.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.background(
											color = Color(0xFFA3E9E4),
											shape = RoundedCornerShape(3.dp)
										)
								){
									Column(
										modifier = Modifier
											.padding(vertical = 3.dp,horizontal = 6.dp,)
									){
										Text("30",
											color = Color(0xFF1D2838),
											fontSize = 10.sp,
										)
									}
								}
								Column(
									modifier = Modifier
										.padding(bottom = 7.dp,)
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
								Column(
									modifier = Modifier
										.clip(shape = RoundedCornerShape(3.dp))
										.width(23.dp)
										.height(23.dp)
								){
								}
							}
						}
					}
					Row(
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier
							.padding(horizontal = 12.dp,)
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 4.dp,)
								.clip(shape = RoundedCornerShape(18597750.dp))
								.width(4.dp)
								.height(4.dp)
								.background(
									color = Color(0xFFD1D5DC),
									shape = RoundedCornerShape(18597750.dp)
								)
						){
						}
						Column(
							modifier = Modifier
								.padding(end = 5.dp,)
								.clip(shape = RoundedCornerShape(18597750.dp))
								.width(4.dp)
								.height(4.dp)
								.background(
									color = Color(0xFFD1D5DC),
									shape = RoundedCornerShape(18597750.dp)
								)
						){
						}
						Column(
							modifier = Modifier
								.clip(shape = RoundedCornerShape(18597750.dp))
								.width(16.dp)
								.height(4.dp)
								.background(
									color = Color(0xFF00D3C2),
									shape = RoundedCornerShape(18597750.dp)
								)
						){
						}
					}
				}
			}
			Column(
				modifier = Modifier
					.padding(bottom = 17.dp,start = 12.dp,end = 12.dp,)
					.fillMaxWidth()
					.padding(top = 4.dp,)
			){
				Text("Rutinas rápidas",
					color = Color(0xFF000000),
					fontSize = 20.sp,
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.padding(bottom = 8.dp,start = 24.dp,)
				)
				Column(
					modifier = Modifier
						.clip(shape = RoundedCornerShape(28.dp))
						.fillMaxWidth()
						.background(
							color = Color(0x80FCFCFF),
							shape = RoundedCornerShape(28.dp)
						)
						.padding(vertical = 10.dp,horizontal = 12.dp,)
				){
					Row(
						modifier = Modifier
							.padding(bottom = 11.dp,)
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 12.dp,)
								.clip(shape = RoundedCornerShape(23.dp))
								.weight(1f)
								.background(
									color = Color(0xFFF1F1F3),
									shape = RoundedCornerShape(23.dp)
								)
								.padding(vertical = 12.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/heu87eix_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(bottom = 15.dp,start = 12.dp,)
									.clip(shape = RoundedCornerShape(23.dp))
									.width(25.dp)
									.height(25.dp)
							)
							Text("Piernas",
								color = Color(0xFF000000),
								fontSize = 12.sp,
								modifier = Modifier
									.padding(start = 12.dp,)
							)
						}
						Column(
							modifier = Modifier
								.padding(end = 13.dp,)
								.clip(shape = RoundedCornerShape(23.dp))
								.weight(1f)
								.background(
									color = Color(0xFFF1F1F3),
									shape = RoundedCornerShape(23.dp)
								)
								.padding(vertical = 12.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/6bbcgz00_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(bottom = 15.dp,start = 13.dp,)
									.clip(shape = RoundedCornerShape(23.dp))
									.width(25.dp)
									.height(25.dp)
							)
							Text("Brazos",
								color = Color(0xFF000000),
								fontSize = 12.sp,
								modifier = Modifier
									.padding(start = 13.dp,)
							)
						}
						Column(
							modifier = Modifier
								.clip(shape = RoundedCornerShape(23.dp))
								.weight(1f)
								.background(
									color = Color(0xFFF1F1F3),
									shape = RoundedCornerShape(23.dp)
								)
								.padding(vertical = 12.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/jm2bt8w9_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(bottom = 15.dp,start = 13.dp,)
									.clip(shape = RoundedCornerShape(23.dp))
									.width(25.dp)
									.height(25.dp)
							)
							Text("Tren Superior",
								color = Color(0xFF000000),
								fontSize = 12.sp,
								modifier = Modifier
									.padding(start = 13.dp,)
							)
						}
					}
					Row(
						modifier = Modifier
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(end = 12.dp,)
								.clip(shape = RoundedCornerShape(23.dp))
								.weight(1f)
								.background(
									color = Color(0xFFF1F1F3),
									shape = RoundedCornerShape(23.dp)
								)
								.padding(vertical = 12.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/ae07ab0c_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(bottom = 15.dp,start = 12.dp,)
									.clip(shape = RoundedCornerShape(23.dp))
									.width(25.dp)
									.height(25.dp)
							)
							Text("Abdomen",
								color = Color(0xFF000000),
								fontSize = 12.sp,
								modifier = Modifier
									.padding(start = 12.dp,)
							)
						}
						Column(
							modifier = Modifier
								.padding(end = 13.dp,)
								.clip(shape = RoundedCornerShape(23.dp))
								.weight(1f)
								.background(
									color = Color(0xFFF1F1F3),
									shape = RoundedCornerShape(23.dp)
								)
								.padding(vertical = 12.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/lycace6g_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(bottom = 15.dp,start = 13.dp,)
									.clip(shape = RoundedCornerShape(23.dp))
									.width(25.dp)
									.height(25.dp)
							)
							Text("Cardio",
								color = Color(0xFF000000),
								fontSize = 12.sp,
								modifier = Modifier
									.padding(start = 13.dp,)
							)
						}
						Column(
							modifier = Modifier
								.clip(shape = RoundedCornerShape(23.dp))
								.weight(1f)
								.background(
									color = Color(0xFFF1F1F3),
									shape = RoundedCornerShape(23.dp)
								)
								.padding(top = 12.dp,bottom = 12.dp,end = 13.dp,)
						){
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/lryu763m_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.padding(bottom = 14.dp,start = 13.dp,)
									.clip(shape = RoundedCornerShape(23.dp))
									.width(25.dp)
									.height(25.dp)
							)
							Row(
								horizontalArrangement = Arrangement.SpaceBetween,
								modifier = Modifier
									.padding(start = 13.dp,)
									.fillMaxWidth()
							){
								Text("Rutinas",
									color = Color(0xFF000000),
									fontSize = 12.sp,
								)
								Text("1",
									color = Color(0xFF000000),
									fontSize = 13.sp,
									fontWeight = FontWeight.Bold,
								)
							}
						}
					}
				}
			}
			Column(
				modifier = Modifier
					.padding(bottom = 1.dp,start = 11.dp,end = 11.dp,)
					.fillMaxWidth()
					.padding(top = 4.dp,)
			){
				Text("Historial de Entrenamientos",
					color = Color(0xFF000000),
					fontSize = 20.sp,
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.padding(bottom = 14.dp,start = 16.dp,)
				)
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(bottom = 10.dp,)
						.clip(shape = RoundedCornerShape(54.dp))
						.fillMaxWidth()
						.background(
							color = Color(0xFFFCFCFF),
							shape = RoundedCornerShape(54.dp)
						)
						.padding(horizontal = 10.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/9o32ivxz_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(end = 10.dp,)
							.clip(shape = RoundedCornerShape(54.dp))
							.width(24.dp)
							.height(24.dp)
					)
					TextFieldView(
						placeholder = "Piernas - 20/11/25",
						value = textField1.value,
						onValueChange = { newText -> textField1.value = newText },
						textStyle = TextStyle(
							color = Color(0xFF000000),
							fontSize = 14.sp,
						),
						modifier = Modifier
							.padding(end = 4.dp,)
							.weight(1f)
							.padding(vertical = 12.dp,)
					)
					Column(
						modifier = Modifier
							.clip(shape = RoundedCornerShape(20.dp))
							.background(
								color = Color(0xFF000000),
								shape = RoundedCornerShape(20.dp)
							)
							.padding(vertical = 4.dp,horizontal = 20.dp,)
					){
						Text("Detalles",
							color = Color(0xFFFFFFFF),
							fontSize = 14.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(bottom = 10.dp,)
						.clip(shape = RoundedCornerShape(54.dp))
						.fillMaxWidth()
						.background(
							color = Color(0xFFFCFCFF),
							shape = RoundedCornerShape(54.dp)
						)
						.padding(horizontal = 10.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/rc1dy2ti_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(end = 10.dp,)
							.clip(shape = RoundedCornerShape(54.dp))
							.width(24.dp)
							.height(24.dp)
					)
					TextFieldView(
						placeholder = "Pecho - 19/11/25",
						value = textField2.value,
						onValueChange = { newText -> textField2.value = newText },
						textStyle = TextStyle(
							color = Color(0xFF000000),
							fontSize = 14.sp,
						),
						modifier = Modifier
							.padding(end = 4.dp,)
							.weight(1f)
							.padding(vertical = 12.dp,)
					)
					Column(
						modifier = Modifier
							.clip(shape = RoundedCornerShape(20.dp))
							.background(
								color = Color(0xFF000000),
								shape = RoundedCornerShape(20.dp)
							)
							.padding(vertical = 4.dp,horizontal = 20.dp,)
					){
						Text("Detalles",
							color = Color(0xFFFFFFFF),
							fontSize = 14.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.padding(bottom = 10.dp,)
						.clip(shape = RoundedCornerShape(54.dp))
						.fillMaxWidth()
						.background(
							color = Color(0xFFFCFCFF),
							shape = RoundedCornerShape(54.dp)
						)
						.padding(horizontal = 10.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/qf1qj042_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(end = 10.dp,)
							.clip(shape = RoundedCornerShape(54.dp))
							.width(24.dp)
							.height(24.dp)
					)
					TextFieldView(
						placeholder = "Brazo y Hombro - 18/11/25",
						value = textField3.value,
						onValueChange = { newText -> textField3.value = newText },
						textStyle = TextStyle(
							color = Color(0xFF000000),
							fontSize = 14.sp,
						),
						modifier = Modifier
							.padding(end = 4.dp,)
							.weight(1f)
							.padding(vertical = 12.dp,)
					)
					Column(
						modifier = Modifier
							.clip(shape = RoundedCornerShape(20.dp))
							.background(
								color = Color(0xFF000000),
								shape = RoundedCornerShape(20.dp)
							)
							.padding(vertical = 4.dp,horizontal = 20.dp,)
					){
						Text("Detalles",
							color = Color(0xFFFFFFFF),
							fontSize = 14.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.clip(shape = RoundedCornerShape(54.dp))
						.fillMaxWidth()
						.background(
							color = Color(0xFFFCFCFF),
							shape = RoundedCornerShape(54.dp)
						)
						.padding(horizontal = 10.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/jl5vzbu3_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(end = 10.dp,)
							.clip(shape = RoundedCornerShape(54.dp))
							.width(24.dp)
							.height(24.dp)
					)
					TextFieldView(
						placeholder = "Piernas - 17/11/25",
						value = textField4.value,
						onValueChange = { newText -> textField4.value = newText },
						textStyle = TextStyle(
							color = Color(0xFF000000),
							fontSize = 14.sp,
						),
						modifier = Modifier
							.padding(end = 4.dp,)
							.weight(1f)
							.padding(vertical = 12.dp,)
					)
					Column(
						modifier = Modifier
							.clip(shape = RoundedCornerShape(20.dp))
							.background(
								color = Color(0xFF000000),
								shape = RoundedCornerShape(20.dp)
							)
							.padding(vertical = 4.dp,horizontal = 20.dp,)
					){
						Text("Detalles",
							color = Color(0xFFFFFFFF),
							fontSize = 14.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
			}
			Row(
				modifier = Modifier
					.padding(bottom = 21.dp,start = 22.dp,end = 22.dp,)
					.clip(shape = RoundedCornerShape(79.dp))
					.fillMaxWidth()
					.background(
						color = Color(0x99FFFFFF),
						shape = RoundedCornerShape(79.dp)
					)
					.shadow(
						elevation = 16.dp,
						spotColor = Color(0x40000000),
					)
					.padding(vertical = 5.dp,horizontal = 6.dp,)
			){
				OutlinedButton(
					onClick = { println("Pressed!") },
					border = BorderStroke(0.dp, Color.Transparent),
					colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
					contentPadding = PaddingValues(),
					modifier = Modifier
						.clip(shape = RoundedCornerShape(79.dp))
						.weight(1f)
						.background(
							color = Color(0x1ADCDCDC),
							shape = RoundedCornerShape(79.dp)
						)
				){
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier
							.padding(vertical = 4.dp,horizontal = 12.dp,)
					){
						CoilImage(
							imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/ifrsda24_expires_30_days.png"},
							imageOptions = ImageOptions(contentScale = ContentScale.Crop),
							modifier = Modifier
								.padding(bottom = 2.dp,)
								.width(54.dp)
								.height(24.dp)
						)
						Text("Inicio",
							color = Color(0xFF000000),
							fontSize = 12.sp,
						)
					}
				}
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.clip(shape = RoundedCornerShape(79.dp))
						.weight(1f)
						.padding(vertical = 4.dp,horizontal = 12.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/4112qzne_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(bottom = 2.dp,)
							.width(54.dp)
							.height(24.dp)
					)
					Text("Estadisticas",
						color = Color(0xFF000000),
						fontSize = 12.sp,
					)
				}
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.clip(shape = RoundedCornerShape(79.dp))
						.weight(1f)
						.padding(vertical = 4.dp,horizontal = 12.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/7c0hsxe4_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(bottom = 2.dp,)
							.width(54.dp)
							.height(24.dp)
					)
					Text("Entrenos",
						color = Color(0xFF000000),
						fontSize = 12.sp,
					)
				}
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.clip(shape = RoundedCornerShape(79.dp))
						.weight(1f)
						.padding(vertical = 4.dp,horizontal = 12.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/zxs46khz_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(bottom = 2.dp,)
							.width(54.dp)
							.height(24.dp)
					)
					Text("Medidas",
						color = Color(0xFF000000),
						fontSize = 12.sp,
					)
				}
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.clip(shape = RoundedCornerShape(79.dp))
						.weight(1f)
						.padding(vertical = 4.dp,horizontal = 12.dp,)
				){
					CoilImage(
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/f1s9uy3h_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(bottom = 2.dp,)
							.width(54.dp)
							.height(24.dp)
					)
					Text("Menu",
						color = Color(0xFF000000),
						fontSize = 12.sp,
					)
				}
			}
		}
	}
}