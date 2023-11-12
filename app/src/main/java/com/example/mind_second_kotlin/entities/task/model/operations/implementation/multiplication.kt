package com.example.mind_second_kotlin.entities.task.model.operations.implementation

import com.example.mind_second_kotlin.entities.task.model.operations._interface.Operation
import java.util.Random


class Multiplication : Operation {

    private val value1: Int = Random().nextInt(10) + 2
    private val value2: Int = Random().nextInt(10) + 2

    override fun getAnswerTaskOperation(): String {
        return (value1 * value2).toString()
    }

    override fun getTaskOperation(): String {
        return "$value1 * $value2"
    }
}