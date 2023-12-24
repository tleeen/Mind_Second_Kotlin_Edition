package com.example.mind_second_kotlin.shared.lib._interface

interface IRepositoryScore {
    suspend fun getScore(): Int
    suspend fun setScore(value: Int)
}