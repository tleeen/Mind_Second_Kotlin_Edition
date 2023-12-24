package com.example.mind_second_kotlin.entities.score.lib

import com.example.mind_second_kotlin.shared.lib._interface.IRepositoryScore
import RepositoryScore as LocalStorage
import com.example.mind_second_kotlin.shared.api.WebRepositoryScore as API

object FactoryScoreRepository {
    fun createInstanceRepository(flag: String): IRepositoryScore {
        return when (flag) {
            "api" -> API()
            "ls" -> LocalStorage
            else -> throw IllegalArgumentException("Invalid flag")
        }
    }
}