package com.example.mind_second_kotlin.entities.score.model

import androidx.compose.runtime.mutableStateOf

class BestScore {
    private var bestScore = mutableStateOf(0);

    fun getBestScore(): Int{
        return bestScore.value;
    }

    fun setBestScore(score: Int) {
        bestScore.value = score
    }
}

object BestScoreFactory {
    private var instance: BestScore? = null

    fun createInstance(): BestScore {
        if (instance === null) {
            instance = BestScore()
        }
        return instance!!
    }
}