package com.example.mind_second_kotlin.entities.score.model
import androidx.compose.runtime.mutableIntStateOf

object RoundScore {
    private var roundScore = mutableIntStateOf(0)

    fun getRoundScore(): Int{
        return roundScore.intValue
    }

    fun setRoundScore(score: Int) {
        roundScore.intValue = score
    }
}