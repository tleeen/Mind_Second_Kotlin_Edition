package com.example.mind_second_kotlin.app

import GameScreen
import LoseScreen
import RepositoryScore
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mind_second_kotlin.pages.start.ui.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RepositoryScore.initialize(this)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "start") {
                composable("start") { StartScreen(navController) }
                composable("lose") { LoseScreen(navController) }
                composable("game") { GameScreen(navController) }
            }
            navController.navigate("start")
        }
    }
}