package com.schwarckstudio.lionfitness.logic

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExportManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun exportAllData() {
        val filesToZip = listOf(
            "routines.csv",
            "workouts.csv",
            "measurements.csv",
            "exercises.csv"
        )

        val zipFileName = "lionfitness_backup_${System.currentTimeMillis()}.zip"
        val cacheZipFile = File(context.cacheDir, zipFileName)
        
        try {
            // 1. Create Zip in Cache
            ZipOutputStream(BufferedOutputStream(FileOutputStream(cacheZipFile))).use { out ->
                filesToZip.forEach { fileName ->
                    val file = File(context.filesDir, fileName)
                    if (file.exists()) {
                        FileInputStream(file).use { fi ->
                            BufferedInputStream(fi).use { origin ->
                                val entry = ZipEntry(fileName)
                                out.putNextEntry(entry)
                                origin.copyTo(out)
                            }
                        }
                    }
                }
            }

            // 2. Save to Downloads
            saveToDownloads(cacheZipFile, zipFileName)

            // 3. Share
            shareFile(cacheZipFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveToDownloads(file: File, fileName: String) {
        val contentValues = android.content.ContentValues().apply {
            put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "application/zip")
            put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_DOWNLOADS)
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

        if (uri != null) {
            resolver.openOutputStream(uri)?.use { outputStream ->
                FileInputStream(file).use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }

    private fun shareFile(file: File) {
        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/zip"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val chooser = Intent.createChooser(intent, "Exportar datos").apply {
             flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(chooser)
    }
}
