package com.schwarckstudio.lionfitness.core.data.csv

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CsvManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun writeCsv(fileName: String, data: List<Map<String, String>>) {
        if (data.isEmpty()) return
        
        val file = File(context.filesDir, fileName)
        val headers = data.first().keys.toList()
        
        file.bufferedWriter().use { writer ->
            writer.write(headers.joinToString(","))
            writer.newLine()
            
            data.forEach { row ->
                val line = headers.joinToString(",") { header ->
                    escapeCsv(row[header] ?: "")
                }
                writer.write(line)
                writer.newLine()
            }
        }
    }

    fun readCsv(fileName: String): List<Map<String, String>> {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) return emptyList()

        val lines = file.readLines()
        if (lines.isEmpty()) return emptyList()

        val headers = lines.first().split(",")
        val result = mutableListOf<Map<String, String>>()

        for (i in 1 until lines.size) {
            val values = parseCsvLine(lines[i])
            if (values.size == headers.size) {
                val row = headers.zip(values).toMap()
                result.add(row)
            }
        }
        return result
    }

    private fun escapeCsv(value: String): String {
        var escaped = value.replace("\"", "\"\"")
        if (escaped.contains(",") || escaped.contains("\"") || escaped.contains("\n")) {
            escaped = "\"$escaped\""
        }
        return escaped
    }

    private fun parseCsvLine(line: String): List<String> {
        val result = mutableListOf<String>()
        var current = StringBuilder()
        var inQuotes = false
        var i = 0
        
        while (i < line.length) {
            val c = line[i]
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length && line[i + 1] == '"') {
                        current.append('"')
                        i++
                    } else {
                        inQuotes = false
                    }
                } else {
                    current.append(c)
                }
            } else {
                if (c == '"') {
                    inQuotes = true
                } else if (c == ',') {
                    result.add(current.toString())
                    current = StringBuilder()
                } else {
                    current.append(c)
                }
            }
            i++
        }
        result.add(current.toString())
        return result
    }
}
