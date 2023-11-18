package com.example.mind_second_kotlin.widgets.roundGame.ui

import Input
import RepositoryScore
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
import com.example.mind_second_kotlin.shared.ui.timer.model.TimerFactory
import com.example.mind_second_kotlin.shared.ui.timer.ui.Timer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskWidget(navController: NavHostController){
    val taskStore = TaskFactory.createInstance()
    val task = taskStore.getTask()

    val functionalTimer = TimerFactory.createInstance()
    val timer =  functionalTimer.getTimerStr()
    val percent =  functionalTimer.getPercent()

    LaunchedEffect(Unit) {
        taskStore.createTask()

        val stateBestScore = BestScoreFactory.createInstance()
        val stateRoundScore = RoundScoreFactory.createInstance()
        stateBestScore.setBestScore(RepositoryScore.getScore())
        stateRoundScore.setRoundScore(0)

        functionalTimer.setFunction {if (stateRoundScore.getRoundScore() > stateBestScore.getBestScore()) {
            RepositoryScore.setScore(stateRoundScore.getRoundScore())
            stateBestScore.setBestScore(stateRoundScore.getRoundScore())
        }
            navController.navigate("lose")}
        functionalTimer.start()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Timer(percent, timer)
        Text(task, fontSize = 30.sp)
        Input(navController)
    }
}
