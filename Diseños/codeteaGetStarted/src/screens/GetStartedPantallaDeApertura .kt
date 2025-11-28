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
@Composable
fun GetStartedPantallaDeApertura () {
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
			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier
					.padding(top = 20.dp,bottom = 12.dp,start = 33.dp,end = 33.dp,)
					.fillMaxWidth()
			){
				CoilImage(
					imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/8yymbdny_expires_30_days.png"},
					imageOptions = ImageOptions(contentScale = ContentScale.Crop),
					modifier = Modifier
						.clip(shape = RoundedCornerShape(20.dp))
						.width(40.dp)
						.height(27.dp)
				)
				CoilImage(
					imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/laqc75pv_expires_30_days.png"},
					imageOptions = ImageOptions(contentScale = ContentScale.Crop),
					modifier = Modifier
						.clip(shape = RoundedCornerShape(20.dp))
						.width(123.dp)
						.height(27.dp)
				)
			}
			Box{
				CoilImage(
					imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/jmif8eqd_expires_30_days.png"},
					imageOptions = ImageOptions(contentScale = ContentScale.Crop),
					modifier = Modifier
						.padding(bottom = 59.dp,)
						.clip(shape = RoundedCornerShape(20.dp))
						.fillMaxWidth()
				)
				Column(
					modifier = Modifier
						.padding(bottom = 59.dp,)
						.clip(shape = RoundedCornerShape(20.dp))
						.fillMaxWidth()
				){
					Text("Entrena, registra tu progreso y supera tus límites con nosotros.",
						color = Color(0xFF000000),
						fontSize = 63.sp,
						fontWeight = FontWeight.Bold,
						modifier = Modifier
							.padding(top = 204.dp,bottom = 93.dp,start = 53.dp,)
							.width(314.dp)
					)
					Row(
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier
							.padding(bottom = 55.dp,start = 43.dp,end = 43.dp,)
							.clip(shape = RoundedCornerShape(153.dp))
							.fillMaxWidth()
							.background(
								color = Color(0xFF010102),
								shape = RoundedCornerShape(153.dp)
							)
							.padding(vertical = 14.dp,horizontal = 20.dp,)
					){
						Text("¡Empieza ahora tu cambio!",
							color = Color(0xFFFFFFFF),
							fontSize = 20.sp,
							fontWeight = FontWeight.Bold,
						)
						CoilImage(
							imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/7SxQbFYrve/g5o5u0wn_expires_30_days.png"},
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