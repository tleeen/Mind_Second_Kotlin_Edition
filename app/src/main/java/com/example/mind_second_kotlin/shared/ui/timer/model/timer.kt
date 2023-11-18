package com.example.mind_second_kotlin.shared.ui.timer.model

import android.os.CountDownTimer
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf

class FunctionalTimer{

    var time = mutableDoubleStateOf(10.0)
    var waitTime = mutableDoubleStateOf(10.0)
    var percent = mutableDoubleStateOf(1.0)
    var timeStr = mutableStateOf("10.0")


    lateinit var timer:  CountDownTimer
    lateinit var callback: () -> Unit

    fun getWaitTime():Double{
        return waitTime.doubleValue
    }

    fun getTimerStr(): String{
        return timeStr.value;
    }

    fun getPercent(): Double{
        return percent.doubleValue;
    }

    fun setFunction(action: () -> Unit) {
        callback = action
    }

    fun calculationTime() {
        percent.doubleValue = waitTime.doubleValue / 10.0
        timeStr.value = String.format("%.1f",waitTime.doubleValue % 60.0)
    }

    fun restart() {
        waitTime.doubleValue = 10.0
        timer.cancel()
        start()
    }

    fun pause() {
        timer.cancel()
        percent.doubleValue = 1.0
        time.doubleValue = 10.0
        waitTime.doubleValue = 10.0
        timeStr.value = "10.0"
    }

    fun start() {
        timer = object: CountDownTimer(10000, 100) {

            var localTime = mutableDoubleStateOf(waitTime.doubleValue)

            override fun onTick(millisUntilFinished: Long) {
                localTime.doubleValue = localTime.doubleValue - 0.1
                waitTime.doubleValue = localTime.doubleValue
                calculationTime()
            }

            override fun onFinish() {
                timeStr.value = "10.0"
                percent.doubleValue = 1.0
                time.doubleValue = 10.0
                waitTime.doubleValue = 10.0
                callback()
                cancel()
            }
        }.start()
    }
}

object TimerFactory {
    private var instance: FunctionalTimer? = null

    fun createInstance(): FunctionalTimer {
        if (instance === null) {
            instance = FunctionalTimer()
        }
        return instance!!
    }
}
