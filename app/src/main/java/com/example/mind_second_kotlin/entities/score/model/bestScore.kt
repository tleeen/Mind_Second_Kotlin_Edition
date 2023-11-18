package com.example.mind_second_kotlin.entities.score.model

import androidx.compose.runtime.mutableIntStateOf

class BestScore {
    private var bestScore = mutableIntStateOf(0);

    fun getBestScore(): Int{
        return bestScore.intValue;
    }

    fun setBestScore(score: Int) {
        bestScore.intValue = score
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