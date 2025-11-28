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
import androidx.compose.ui.text.style.*
import androidx.compose.ui.layout.*
import com.skydoves.landscapist.*
import com.skydoves.landscapist.coil3.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.geometry.Offset
@Composable
fun Entrenamientos() {
	val textField1 = remember { mutableStateOf("") }
	val textField2 = remember { mutableStateOf("") }
	val textField3 = remember { mutableStateOf("") }
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
						imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/01bfztwu_expires_30_days.png"},
						imageOptions = ImageOptions(contentScale = ContentScale.Crop),
						modifier = Modifier
							.padding(end = 18.dp,)
							.width(50.dp)
							.height(50.dp)
					)
					Column(
					){
						Text("Entrenamientos",
							color = Color(0xFF000000),
							fontSize = 20.sp,
							fontWeight = FontWeight.Bold,
						)
					}
				}
				CoilImage(
					imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/2ke5ny84_expires_30_days.png"},
					imageOptions = ImageOptions(contentScale = ContentScale.Crop),
					modifier = Modifier
						.width(100.dp)
						.height(50.dp)
				)
			}
			Column(
				modifier = Modifier
					.height(881.dp)
					.fillMaxWidth()
			){
				Column(
					modifier = Modifier
						.padding(bottom = 20.dp,)
						.fillMaxWidth()
						.weight(1f)
				){
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier
							.padding(bottom = 12.dp,)
							.fillMaxWidth()
							.padding(horizontal = 18.dp,)
					){
						Column(
							modifier = Modifier
								.padding(bottom = 1.dp,)
						){
							Text("Destacados",
								color = Color(0xFF000000),
								fontSize = 18.sp,
								fontWeight = FontWeight.Bold,
							)
						}
						CoilImage(
							imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/wfr2bqq1_expires_30_days.png"},
							imageOptions = ImageOptions(contentScale = ContentScale.Crop),
							modifier = Modifier
								.width(20.dp)
								.height(20.dp)
						)
					}
					Column(
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f)
							.padding(bottom = 4.dp,)
					){
						Row(
							modifier = Modifier
								.fillMaxWidth()
								.horizontalScroll(rememberScrollState())
						){
							Column(
								modifier = Modifier
									.padding(start = 18.dp,end = 12.dp,)
									.clip(shape = RoundedCornerShape(24.dp))
									.background(
										color = Color(0xB0FFFFFF),
										shape = RoundedCornerShape(24.dp)
									)
									.shadow(
										elevation = 20.dp,
										spotColor = Color(0x1A000000),
									)
									.padding(18.dp)
							){
								Row(
									modifier = Modifier
										.padding(bottom = 12.dp,)
								){
									CoilImage(
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/fsyvmvdc_expires_30_days.png"},
										imageOptions = ImageOptions(contentScale = ContentScale.Crop),
										modifier = Modifier
											.padding(end = 97.dp,)
											.width(48.dp)
											.height(48.dp)
									)
									OutlinedButton(
										onClick = { println("Pressed!") },
										border = BorderStroke(0.dp, Color.Transparent),
										colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
										contentPadding = PaddingValues(),
										modifier = Modifier
											.clip(shape = RoundedCornerShape(26843500.dp))
											.background(
												color = Color(0x0D000000),
												shape = RoundedCornerShape(26843500.dp)
											)
									){
										Column(
											modifier = Modifier
												.padding(vertical = 4.dp,horizontal = 10.dp,)
										){
											Text("Mayor Peso",
												color = Color(0xFF000000),
												fontSize = 11.sp,
												fontWeight = FontWeight.Bold,
											)
										}
									}
								}
								Column(
									modifier = Modifier
										.padding(bottom = 4.dp,)
										.padding(bottom = 1.dp,)
								){
									Text("Piernas Destruidas",
										color = Color(0xFF000000),
										fontSize = 16.sp,
										fontWeight = FontWeight.Bold,
									)
								}
								Column(
									modifier = Modifier
										.padding(bottom = 2.dp,)
										.padding(bottom = 1.dp,)
								){
									Text("24,899.9 kg",
										color = Color(0xFF000000),
										fontSize = 24.sp,
										fontWeight = FontWeight.Bold,
									)
								}
								Column(
									modifier = Modifier
										.padding(bottom = 1.dp,)
								){
									Text("Volumen total",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
								}
							}
							Column(
								modifier = Modifier
									.clip(shape = RoundedCornerShape(24.dp))
									.background(
										color = Color(0xB0FFFFFF),
										shape = RoundedCornerShape(24.dp)
									)
									.shadow(
										elevation = 20.dp,
										spotColor = Color(0x1A000000),
									)
									.padding(18.dp)
							){
								Row(
									modifier = Modifier
										.padding(bottom = 12.dp,)
								){
									CoilImage(
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/trtcewv7_expires_30_days.png"},
										imageOptions = ImageOptions(contentScale = ContentScale.Crop),
										modifier = Modifier
											.padding(end = 97.dp,)
											.width(48.dp)
											.height(48.dp)
									)
									OutlinedButton(
										onClick = { println("Pressed!") },
										border = BorderStroke(0.dp, Color.Transparent),
										colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
										contentPadding = PaddingValues(),
										modifier = Modifier
											.clip(shape = RoundedCornerShape(26843500.dp))
											.background(
												color = Color(0x0D000000),
												shape = RoundedCornerShape(26843500.dp)
											)
									){
										Column(
											modifier = Modifier
												.padding(vertical = 4.dp,horizontal = 10.dp,)
										){
											Text("Mayor Duración",
												color = Color(0xFF000000),
												fontSize = 11.sp,
												fontWeight = FontWeight.Bold,
											)
										}
									}
								}
								Column(
									modifier = Modifier
										.padding(bottom = 4.dp,)
										.padding(bottom = 1.dp,)
								){
									Text("Cardio Extremo",
										color = Color(0xFF000000),
										fontSize = 16.sp,
										fontWeight = FontWeight.Bold,
									)
								}
								Column(
									modifier = Modifier
										.padding(bottom = 2.dp,)
										.padding(bottom = 1.dp,)
								){
									Text("2h 15min",
										color = Color(0xFF000000),
										fontSize = 24.sp,
										fontWeight = FontWeight.Bold,
									)
								}
								Column(
									modifier = Modifier
										.padding(bottom = 1.dp,)
								){
									Text("Tiempo récord",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
								}
							}
						}
					}
				}
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 18.dp,)
				){
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier
							.padding(bottom = 16.dp,)
							.fillMaxWidth()
					){
						Column(
							modifier = Modifier
								.padding(bottom = 1.dp,)
						){
							Text("Historial",
								color = Color(0xFF000000),
								fontSize = 18.sp,
								fontWeight = FontWeight.Bold,
							)
						}
						Text("Ver todo",
							color = Color(0xFF4C6EF5),
							fontSize = 13.sp,
							fontWeight = FontWeight.Bold,
						)
					}
					Column(
						modifier = Modifier
							.fillMaxWidth()
							.padding(bottom = 1.dp,)
					){
						Box(
							modifier = Modifier
								.padding(bottom = 16.dp,)
						){
							Column(
								modifier = Modifier
									.fillMaxWidth()
							){
								Column(
									modifier = Modifier
										.clip(shape = RoundedCornerShape(24.dp))
										.fillMaxWidth()
										.background(
											color = Color(0xB0FFFFFF),
											shape = RoundedCornerShape(24.dp)
										)
										.shadow(
											elevation = 20.dp,
											spotColor = Color(0x1A000000),
										)
										.padding(top = 20.dp,bottom = 20.dp,end = 34.dp,)
								){
									Row(
										horizontalArrangement = Arrangement.SpaceBetween,
										verticalAlignment = Alignment.CenterVertically,
										modifier = Modifier
											.padding(bottom = 16.dp,start = 18.dp,)
											.fillMaxWidth()
									){
										Row(
											verticalAlignment = Alignment.CenterVertically,
										){
											Column(
												modifier = Modifier
													.padding(end = 12.dp,)
													.clip(shape = RoundedCornerShape(26843500.dp))
													.background(
														brush = Brush.linearGradient(
															colors = listOf(Color(0xFF4C6EF5), Color(0xFF845EF7), ),
															start = Offset.Zero,
															end = Offset(0F,Float.POSITIVE_INFINITY),
														)
													)
													.padding(top = 11.dp,bottom = 10.dp,start = 12.dp,end = 12.dp,)
											){
												Text("AJ",
													color = Color(0xFFFFFFFF),
													fontSize = 16.sp,
													fontWeight = FontWeight.Bold,
												)
											}
											Column(
											){
												Column(
													modifier = Modifier
														.padding(top = 1.dp,)
												){
													Text("alanjose2013",
														color = Color(0xFF000000),
														fontSize = 14.sp,
														fontWeight = FontWeight.Bold,
													)
												}
												Column(
												){
													Text("hace 4 días",
														color = Color(0xFF000000),
														fontSize = 12.sp,
													)
												}
											}
										}
										CoilImage(
											imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/yesjcktm_expires_30_days.png"},
											imageOptions = ImageOptions(contentScale = ContentScale.Crop),
											modifier = Modifier
												.width(31.dp)
												.height(32.dp)
										)
									}
									Column(
										modifier = Modifier
											.padding(bottom = 14.dp,start = 18.dp,)
											.padding(bottom = 1.dp,)
									){
										Text("Piernas destruidas",
											color = Color(0xFF000000),
											fontSize = 20.sp,
											fontWeight = FontWeight.Bold,
										)
									}
									Row(
										modifier = Modifier
											.padding(bottom = 16.dp,start = 18.dp,)
											.fillMaxWidth()
									){
										Column(
											modifier = Modifier
												.padding(end = 8.dp,)
												.clip(shape = RoundedCornerShape(12.dp))
												.weight(1f)
												.background(
													color = Color(0x05000000),
													shape = RoundedCornerShape(12.dp)
												)
												.padding(horizontal = 10.dp,)
										){
											Column(
												modifier = Modifier
													.padding(top = 10.dp,bottom = 2.dp,)
													.fillMaxWidth()
													.padding(bottom = 1.dp,)
											){
												Text("Tiempo",
													color = Color(0xFF000000),
													fontSize = 10.sp,
												)
											}
											Column(
												horizontalAlignment = Alignment.CenterHorizontally,
												modifier = Modifier
													.padding(bottom = 30.dp,)
													.fillMaxWidth()
													.padding(bottom = 1.dp,)
											){
												Text("1h 58min",
													color = Color(0xFF000000),
													fontSize = 13.sp,
													fontWeight = FontWeight.Bold,
												)
											}
										}
										Column(
											modifier = Modifier
												.padding(end = 8.dp,)
												.clip(shape = RoundedCornerShape(12.dp))
												.weight(1f)
												.background(
													color = Color(0x05000000),
													shape = RoundedCornerShape(12.dp)
												)
												.padding(10.dp)
										){
											Column(
												modifier = Modifier
													.padding(bottom = 2.dp,)
													.fillMaxWidth()
													.padding(bottom = 1.dp,)
											){
												Text("Volumen",
													color = Color(0xFF000000),
													fontSize = 10.sp,
												)
											}
											Column(
												modifier = Modifier
													.fillMaxWidth()
													.padding(bottom = 1.dp,)
											){
												Text("24,899.9 kg",
													color = Color(0xFF000000),
													fontSize = 13.sp,
													fontWeight = FontWeight.Bold,
													modifier = Modifier
														.width(36.dp)
												)
											}
										}
										Column(
											modifier = Modifier
												.padding(end = 8.dp,)
												.clip(shape = RoundedCornerShape(12.dp))
												.weight(1f)
												.background(
													color = Color(0x05000000),
													shape = RoundedCornerShape(12.dp)
												)
												.padding(vertical = 17.dp,)
										){
											Column(
												horizontalAlignment = Alignment.CenterHorizontally,
												modifier = Modifier
													.padding(bottom = 2.dp,start = 23.dp,end = 23.dp,)
													.fillMaxWidth()
											){
												Text("Récords",
													color = Color(0xFF000000),
													fontSize = 10.sp,
												)
											}
											Row(
												verticalAlignment = Alignment.CenterVertically,
												modifier = Modifier
													.padding(horizontal = 24.dp,)
													.fillMaxWidth()
											){
												Column(
													horizontalAlignment = Alignment.CenterHorizontally,
													modifier = Modifier
														.padding(end = 5.dp,)
														.weight(1f)
												){
													Text("🏆",
														color = Color(0xFF0A0A0A),
														fontSize = 16.sp,
													)
												}
												Column(
												){
													Text("9",
														color = Color(0xFF000000),
														fontSize = 13.sp,
														fontWeight = FontWeight.Bold,
													)
												}
											}
										}
										Column(
											horizontalAlignment = Alignment.CenterHorizontally,
											modifier = Modifier
												.clip(shape = RoundedCornerShape(12.dp))
												.weight(1f)
												.background(
													color = Color(0x05000000),
													shape = RoundedCornerShape(12.dp)
												)
												.padding(vertical = 17.dp,)
										){
											Column(
												modifier = Modifier
													.padding(bottom = 2.dp,)
											){
												Text("LPM",
													color = Color(0xFF000000),
													fontSize = 10.sp,
												)
											}
											Row(
												horizontalArrangement = Arrangement.Center,
												verticalAlignment = Alignment.CenterVertically,
												modifier = Modifier
													.padding(horizontal = 19.dp,)
													.fillMaxWidth()
											){
												Column(
													modifier = Modifier
														.padding(end = 5.dp,)
												){
													Text("❤️",
														color = Color(0xFF0A0A0A),
														fontSize = 16.sp,
													)
												}
												Column(
												){
													Text("114",
														color = Color(0xFF000000),
														fontSize = 13.sp,
														fontWeight = FontWeight.Bold,
													)
												}
											}
										}
									}
									Column(
										modifier = Modifier
											.padding(bottom = 19.dp,start = 18.dp,)
											.fillMaxWidth()
									){
										Row(
											verticalAlignment = Alignment.CenterVertically,
											modifier = Modifier
												.padding(bottom = 10.dp,)
												.clip(shape = RoundedCornerShape(14.dp))
												.fillMaxWidth()
												.background(
													color = Color(0x03000000),
													shape = RoundedCornerShape(14.dp)
												)
												.padding(horizontal = 10.dp,)
										){
											CoilImage(
												imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/1krmlgnf_expires_30_days.png"},
												imageOptions = ImageOptions(contentScale = ContentScale.Crop),
												modifier = Modifier
													.padding(end = 12.dp,)
													.clip(shape = RoundedCornerShape(14.dp))
													.width(40.dp)
													.height(40.dp)
											)
											TextFieldView(
												placeholder = "4 series Curl de Pierna Sentado",
												value = textField1.value,
												onValueChange = { newText -> textField1.value = newText },
												textStyle = TextStyle(
													color = Color(0xFF000000),
													fontSize = 13.sp,
												),
												modifier = Modifier
													.padding(end = 4.dp,)
													.weight(1f)
													.padding(vertical = 21.dp,)
											)
										}
										Row(
											verticalAlignment = Alignment.CenterVertically,
											modifier = Modifier
												.padding(bottom = 10.dp,)
												.clip(shape = RoundedCornerShape(14.dp))
												.fillMaxWidth()
												.background(
													color = Color(0x03000000),
													shape = RoundedCornerShape(14.dp)
												)
												.padding(horizontal = 10.dp,)
										){
											CoilImage(
												imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/penc61km_expires_30_days.png"},
												imageOptions = ImageOptions(contentScale = ContentScale.Crop),
												modifier = Modifier
													.padding(end = 12.dp,)
													.clip(shape = RoundedCornerShape(14.dp))
													.width(40.dp)
													.height(40.dp)
											)
											TextFieldView(
												placeholder = "4 series Aducción de Caderas",
												value = textField2.value,
												onValueChange = { newText -> textField2.value = newText },
												textStyle = TextStyle(
													color = Color(0xFF000000),
													fontSize = 13.sp,
												),
												modifier = Modifier
													.padding(end = 4.dp,)
													.weight(1f)
													.padding(vertical = 21.dp,)
											)
										}
										Row(
											verticalAlignment = Alignment.CenterVertically,
											modifier = Modifier
												.padding(bottom = 10.dp,)
												.clip(shape = RoundedCornerShape(14.dp))
												.fillMaxWidth()
												.background(
													color = Color(0x03000000),
													shape = RoundedCornerShape(14.dp)
												)
												.padding(horizontal = 10.dp,)
										){
											CoilImage(
												imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/inhufarf_expires_30_days.png"},
												imageOptions = ImageOptions(contentScale = ContentScale.Crop),
												modifier = Modifier
													.padding(end = 12.dp,)
													.clip(shape = RoundedCornerShape(14.dp))
													.width(40.dp)
													.height(40.dp)
											)
											TextFieldView(
												placeholder = "4 series Abducción de Caderas",
												value = textField3.value,
												onValueChange = { newText -> textField3.value = newText },
												textStyle = TextStyle(
													color = Color(0xFF000000),
													fontSize = 13.sp,
												),
												modifier = Modifier
													.padding(end = 4.dp,)
													.weight(1f)
													.padding(vertical = 21.dp,)
											)
										}
										Column(
											horizontalAlignment = Alignment.CenterHorizontally,
											modifier = Modifier
												.fillMaxWidth()
												.padding(vertical = 8.dp,)
										){
											Column(
											){
												Text("Ver 4 más ejercicios",
													color = Color(0xFF4C6EF5),
													fontSize = 13.sp,
													fontWeight = FontWeight.Bold,
													textAlign = TextAlign.Center,
													modifier = Modifier
														.width(58.dp)
												)
											}
										}
									}
									Row(
										modifier = Modifier
											.padding(start = 18.dp,)
											.fillMaxWidth()
									){
										Row(
											verticalAlignment = Alignment.CenterVertically,
											modifier = Modifier
												.padding(end = 16.dp,)
												.clip(shape = RoundedCornerShape(12.dp))
												.padding(vertical = 8.dp,horizontal = 12.dp,)
										){
											CoilImage(
												imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/0avhx0y2_expires_30_days.png"},
												imageOptions = ImageOptions(contentScale = ContentScale.Crop),
												modifier = Modifier
													.padding(end = 6.dp,)
													.clip(shape = RoundedCornerShape(12.dp))
													.width(20.dp)
													.height(20.dp)
											)
											Text("Me gusta",
												color = Color(0xFF000000),
												fontSize = 13.sp,
												fontWeight = FontWeight.Bold,
											)
										}
										Row(
											verticalAlignment = Alignment.CenterVertically,
											modifier = Modifier
												.padding(end = 16.dp,)
												.clip(shape = RoundedCornerShape(12.dp))
												.weight(1f)
												.padding(vertical = 8.dp,)
										){
											CoilImage(
												imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/sckk0blo_expires_30_days.png"},
												imageOptions = ImageOptions(contentScale = ContentScale.Crop),
												modifier = Modifier
													.padding(start = 12.dp,end = 6.dp,)
													.clip(shape = RoundedCornerShape(12.dp))
													.width(20.dp)
													.height(20.dp)
											)
											Text("Comentar",
												color = Color(0xFF000000),
												fontSize = 13.sp,
												fontWeight = FontWeight.Bold,
											)
										}
										CoilImage(
											imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/gu866l5u_expires_30_days.png"},
											imageOptions = ImageOptions(contentScale = ContentScale.Crop),
											modifier = Modifier
												.width(43.dp)
												.height(36.dp)
										)
									}
								}
							}
							Column(
								modifier = Modifier
									.offset(x = 4.dp, y = 36.dp)
									.align(Alignment.BottomStart)
									.padding(start = 4.dp, bottom = 36.dp)
									.clip(shape = RoundedCornerShape(79.dp))
									.fillMaxWidth()
									.background(
										color = Color(0x26FFFFFF),
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
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/ewfoqxjk_expires_30_days.png"},
										imageOptions = ImageOptions(contentScale = ContentScale.Crop),
										modifier = Modifier
											.padding(end = 24.dp,)
											.height(24.dp)
											.weight(1f)
									)
									CoilImage(
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/1v72wsrn_expires_30_days.png"},
										imageOptions = ImageOptions(contentScale = ContentScale.Crop),
										modifier = Modifier
											.padding(end = 24.dp,)
											.height(24.dp)
											.weight(1f)
									)
									CoilImage(
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/0y8mspfb_expires_30_days.png"},
										imageOptions = ImageOptions(contentScale = ContentScale.Crop),
										modifier = Modifier
											.padding(end = 24.dp,)
											.height(24.dp)
											.weight(1f)
									)
									CoilImage(
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/1dxgnv8o_expires_30_days.png"},
										imageOptions = ImageOptions(contentScale = ContentScale.Crop),
										modifier = Modifier
											.padding(end = 24.dp,)
											.height(24.dp)
											.weight(1f)
									)
									CoilImage(
										imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/d1j7hq52_expires_30_days.png"},
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
									Text("Inicio",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
									Column(
										modifier = Modifier
											.weight(1f)
									){
									}
									Text("Estadisticas",
										color = Color(0xFF000000),
										fontSize = 12.sp,
										modifier = Modifier
											.padding(end = 24.dp,)
									)
									Text("Entrenos",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
									Column(
										modifier = Modifier
											.weight(1f)
									){
									}
									Text("Medidas",
										color = Color(0xFF000000),
										fontSize = 12.sp,
									)
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
						Box{
							Column(
								modifier = Modifier
									.fillMaxWidth()
							){
								Column(
									modifier = Modifier
										.clip(shape = RoundedCornerShape(24.dp))
										.fillMaxWidth()
										.background(
											color = Color(0xB0FFFFFF),
											shape = RoundedCornerShape(24.dp)
										)
										.shadow(
											elevation = 20.dp,
											spotColor = Color(0x1A000000),
										)
										.padding(start = 18.dp,)
								){
									Column(
										modifier = Modifier
											.padding(top = 18.dp,)
											.clip(shape = RoundedCornerShape(26843500.dp))
											.width(44.dp)
											.height(22.dp)
											.background(
												brush = Brush.linearGradient(
													colors = listOf(Color(0xFF4C6EF5), Color(0xFF845EF7), ),
													start = Offset.Zero,
													end = Offset(0F,Float.POSITIVE_INFINITY),
												)
											)
									){
									}
								}
							}
							Text("alanjose2013",
								color = Color(0xFF000000),
								fontSize = 14.sp,
								fontWeight = FontWeight.Bold,
								modifier = Modifier
									.offset(x = 74.dp, y = 0.dp)
									.align(Alignment.BottomStart)
									.padding(start = 74.dp)
							)
							CoilImage(
								imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/runz5g0f_expires_30_days.png"},
								imageOptions = ImageOptions(contentScale = ContentScale.Crop),
								modifier = Modifier
									.offset(x = -34.dp, y = 0.dp)
									.align(Alignment.BottomEnd)
									.width(31.dp)
									.height(16.dp)
							)
							Text("AJ",
								color = Color(0xFFFFFFFF),
								fontSize = 16.sp,
								fontWeight = FontWeight.Bold,
								modifier = Modifier
									.offset(x = 30.dp, y = 0.dp)
									.align(Alignment.BottomStart)
									.padding(start = 30.dp)
							)
						}
					}
				}
			}
		}
	}
}