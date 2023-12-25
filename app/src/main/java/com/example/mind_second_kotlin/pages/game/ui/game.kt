package com.example.mind_second_kotlin.pages.game.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.widgets.roundGame.ui.TaskWidget

@Composable
fun GameScreen(navController: NavHostController) {
    TaskWidget(navController)
}
