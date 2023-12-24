package com.example.mind_second_kotlin.entities.score.model

import androidx.compose.runtime.mutableIntStateOf
import com.example.mind_second_kotlin.entities.score.lib.FactoryScoreRepository
import com.example.mind_second_kotlin.shared.lib._interface.IRepositoryScore

class BestScore {
    private var bestScore = mutableIntStateOf(0);
    private var repositoryScore: IRepositoryScore = FactoryScoreRepository.createInstanceRepository("api");

    fun getBestScore(): Int{
        return bestScore.intValue
    }

    suspend fun setBestScore(score: Int) {
        repositoryScore.setScore(score)
        bestScore.intValue = score
    }

    suspend fun initBestScore(){
        bestScore.intValue = repositoryScore.getScore()
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