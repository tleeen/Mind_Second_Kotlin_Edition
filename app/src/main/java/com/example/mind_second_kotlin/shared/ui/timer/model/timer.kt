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
    lateinit var _callback: () -> Unit

    fun getWaitTime():Double{
        return waitTime.doubleValue
    }

    fun getTimerStr(): String{
        return timeStr.value;
    }

    fun getPercent(): Double{
        return percent.doubleValue;
    }

    fun _setTimeStr(value: String) {
        timeStr.value = value
    }

    fun setCallback(action: () -> Unit) {
        _callback = action
    }

    fun _calculationTime() {
        percent.doubleValue = getWaitTime() / 10.0
        timeStr.value = String.format("%.1f",getWaitTime() % 60.0)
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
        _setTimeStr("10.0")
    }

    fun start() {
        timer = object: CountDownTimer(10000, 100) {

            var localTime = mutableDoubleStateOf(waitTime.doubleValue)

            override fun onTick(millisUntilFinished: Long) {
                localTime.doubleValue = localTime.doubleValue - 0.1
                waitTime.doubleValue = localTime.doubleValue
                _calculationTime()
            }

            override fun onFinish() {
                _setTimeStr("10.0")
                percent.doubleValue = 1.0
                time.doubleValue = 10.0
                waitTime.doubleValue = 10.0
                _callback()
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
