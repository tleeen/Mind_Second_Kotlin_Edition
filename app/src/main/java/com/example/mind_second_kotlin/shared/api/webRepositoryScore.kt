package com.example.mind_second_kotlin.shared.api

import com.example.mind_second_kotlin.shared.lib._interface.IRepositoryScore
import com.example.mind_second_kotlin.shared.lib.deviceId.DeviceId
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class WebRepositoryScore : IRepositoryScore {

    override suspend fun getScore(): Int = suspendCoroutine { continuation ->
        val deviceId = DeviceId.getId()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.0.102:8876/api/score/$deviceId")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) continuation.resume(0)
            val responseBody = response.body?.string()
            val jsonObject = responseBody?.let { JSONObject(it) }
            val score = jsonObject!!.getInt("score")
            continuation.resume(score)
        }
    }

    override suspend fun setScore(value: Int) {
        val deviceId = DeviceId.getId()

        val client = OkHttpClient()
        val requestBody = JSONObject().apply {
            put("id_phone", deviceId)
            put("score", value)
        }.toString().toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("http://192.168.0.102:8876/api/score")
            .method("PATCH", requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("Failed to add best score. Error code: ${response.code}")
            }
        }
    }
}