package com.example.mind_second_kotlin.entities.task.model.operations.implementation

import com.example.mind_second_kotlin.entities.task.model.operations._interface.Operation
import java.util.Random
import kotlin.math.roundToInt


class Division : Operation {

    private var value1: Int = 0
    private var value2: Int = 1

    override fun getAnswerTaskOperation(): String {

        while((value1 % value2 != 0) || (value1 < value2)){
            value1 = Random().nextInt(90) + 10
            value2 = Random().nextInt(10) + 2
        }

        return (value1 / value2).toDouble().roundToInt().toString()
    }

    override fun getTaskOperation(): String {
        return "$value1 : $value2"
    }
}