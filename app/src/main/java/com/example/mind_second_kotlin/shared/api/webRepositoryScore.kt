package com.example.mind_second_kotlin.shared.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mind_second_kotlin.shared.lib._interface.IRepositoryScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WebRepositoryScore : IRepositoryScore {
    @RequiresApi(34)
    override suspend fun getScore(): Int = suspendCoroutine { continuation ->
        runBlocking(Dispatchers.IO) {
            val deviceId = Build.DEVICE.hashCode()

            val url = URL("http://10.0.2.2:8876/api/score/$deviceId")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()
                var inputLine: String?
                while (bufferedReader.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                bufferedReader.close()
                inputStream.close()

                val responseBody = JSONObject(response.toString())
                val score = responseBody.getInt("score")
                continuation.resume(score)
            } else {
                continuation.resume(0)
            }
        }
    }
    @RequiresApi(34)
    override suspend fun setScore(value: Int) {
            val deviceId = Build.DEVICE.hashCode()

            val url = URL("http://10.0.2.2:8876/api/score")
            val connection = withContext(Dispatchers.IO) {
                url.openConnection()
            } as HttpURLConnection
            connection.requestMethod = "PATCH"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val data = JSONObject().apply {
                put("id_phone", deviceId)
                put("score", value)
            }.toString().toByteArray()

            connection.outputStream.use { os ->
                os.write(data)
            }

            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw Exception("Failed to add best score. Error code: $responseCode")
            }
    }
}