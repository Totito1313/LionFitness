package com.schwarckstudio.lionfitness.ui.screens.workout

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.schwarckstudio.lionfitness.core.model.WorkoutLog
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem
import java.io.File
import java.io.FileOutputStream

@Composable
fun ShareWorkoutCard(
    workout: WorkoutLog,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(300.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Black) // Premium dark background
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "LION FITNESS",
            color = DesignSystem.Colors.Primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Workout Name
        Text(
            text = workout.name.ifEmpty { "Entrenamiento" },
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Date
        Text(
            text = formatDate(workout.startTime.toDate()),
            color = Color.Gray,
            fontSize = 12.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Stats Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShareStat("VOLUMEN", "${workout.totalVolume.toInt()} kg")
            ShareStat("TIEMPO", formatDuration(workout))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShareStat("SERIES", "${workout.totalSets}")
            ShareStat("RÉCORDS", "${workout.records}")
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Footer
        Text(
            text = "Supera tus límites.",
            color = Color.Gray,
            fontSize = 12.sp,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
        )
    }
}

@Composable
fun ShareStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            color = DesignSystem.Colors.Primary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Helper function to share bitmap
fun shareBitmap(context: Context, bitmap: Bitmap) {
    try {
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val stream = FileOutputStream("$cachePath/workout_share.png")
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()

        val imagePath = File(context.cacheDir, "images")
        val newFile = File(imagePath, "workout_share.png")
        val contentUri: Uri = FileProvider.getUriForFile(
            context,
            "com.schwarckstudio.lionfitness.fileprovider",
            newFile
        )

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setDataAndType(contentUri, context.contentResolver.getType(contentUri))
            putExtra(Intent.EXTRA_STREAM, contentUri)
            type = "image/png"
        }
        context.startActivity(Intent.createChooser(shareIntent, "Compartir entrenamiento"))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
