package com.example.mind_second_kotlin.app.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mind_second_kotlin.pages.game.ui.GameScreen
import com.example.mind_second_kotlin.pages.lose.ui.LoseScreen
import com.example.mind_second_kotlin.pages.start.ui.StartScreen

@Composable
fun Routes(navController:NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("lose") { LoseScreen(navController) }
        composable("game") { GameScreen(navController) }
    }
    navController.navigate("start")
}