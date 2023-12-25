package com.example.mind_second_kotlin.features.enterAndCheckAnswer.model

import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.entities.score.model.BestScore
import com.example.mind_second_kotlin.entities.score.model.RoundScore
import com.example.mind_second_kotlin.entities.task.model.task.Task
import com.example.mind_second_kotlin.entities.timer.model.FunctionalTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun analysisAnswer(answer: String, navController: NavHostController) {
    val task = Task
    val timer = FunctionalTimer
    val stateBestScore = BestScore
    val stateRoundScore = RoundScore

    if (answer == task.getAnswerForTask()) {
        task.createTask()
        stateRoundScore.setRoundScore(stateRoundScore.getRoundScore() + 1)
        timer.restart()
    } else {
            if (stateRoundScore.getRoundScore() > stateBestScore.getBestScore()) {
                CoroutineScope(Dispatchers.IO).launch {
                    stateBestScore.setBestScore(stateRoundScore.getRoundScore())
                }
            }
            navController.navigate("lose")
            timer.pause()
    }
}