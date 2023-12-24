package com.example.mind_second_kotlin.widgets.roundGame.ui

import Input
import RoundScoreFactory
import TaskFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.entities.score.model.BestScoreFactory
import com.example.mind_second_kotlin.entities.timer.model.TimerFactory
import com.example.mind_second_kotlin.entities.timer.ui.Timer
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskWidget(navController: NavHostController){

    val taskStore = TaskFactory.createInstance()
    val functionalTimer = TimerFactory.createInstance()
    val stateBestScore = BestScoreFactory.createInstance()
    val stateRoundScore = RoundScoreFactory.createInstance()

    LaunchedEffect(Unit) {
        taskStore.createTask()
        stateRoundScore.setRoundScore(0)
            functionalTimer.setFunction {
                runBlocking {
                    val bestScore = async { stateBestScore.getBestScore() }
                    println(bestScore.await())
                    if (stateRoundScore.getRoundScore() > stateBestScore.getBestScore()) {
                        stateBestScore.setBestScore(stateRoundScore.getRoundScore())
                    }
                    navController.navigate("lose")
                }
            }
            functionalTimer.start()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Timer(functionalTimer.getPercent(), functionalTimer.getTimerStr())
        Text(taskStore.getTask(), fontSize = 30.sp)
        Input(navController)
    }
}
