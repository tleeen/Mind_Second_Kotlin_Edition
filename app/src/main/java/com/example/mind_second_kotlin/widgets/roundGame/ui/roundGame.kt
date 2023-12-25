package com.example.mind_second_kotlin.widgets.roundGame.ui

import com.example.mind_second_kotlin.features.enterAndCheckAnswer.ui.Input
import com.example.mind_second_kotlin.entities.score.model.RoundScoreFactory
import com.example.mind_second_kotlin.entities.task.model.task.TaskFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.entities.score.model.BestScoreFactory
import com.example.mind_second_kotlin.entities.timer.model.TimerFactory
import com.example.mind_second_kotlin.entities.timer.ui.Timer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskWidget(navController: NavHostController){

    val taskStore = remember { TaskFactory.createInstance() }
    val functionalTimer = remember { TimerFactory.createInstance() }
    val stateBestScore = remember { BestScoreFactory.createInstance() }
    val stateRoundScore = remember { RoundScoreFactory.createInstance() }

    LaunchedEffect(Unit) {
        taskStore.createTask()
        stateRoundScore.setRoundScore(0)
            functionalTimer.setFunction {
                    if (stateRoundScore.getRoundScore() > stateBestScore.getBestScore()) {
                        CoroutineScope(Dispatchers.IO).launch {
                            stateBestScore.setBestScore(stateRoundScore.getRoundScore())
                        }
                    }
                    navController.navigate("lose")
                }
            functionalTimer.start()
        CoroutineScope(Dispatchers.IO).launch {
            stateBestScore.initBestScore()
        }
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
