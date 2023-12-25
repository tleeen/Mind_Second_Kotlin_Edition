package com.example.mind_second_kotlin.shared.lib.deviceId

import android.os.Build

object DeviceId {
    fun getId(): String {
        return Build.DEVICE.hashCode().toString()
    }
}