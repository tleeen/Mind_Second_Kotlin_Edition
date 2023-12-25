package com.example.mind_second_kotlin.pages.start.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.shared.ui.button.ui.Button

@Composable
fun StartScreen(navController: NavHostController) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceAround,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Mind&Second",
      fontSize = 34.sp,
      textAlign = TextAlign.Center,
      modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
    Button(
      action = { navController.navigate("game") },
      icon = Icons.Filled.ArrowForward,
    )
  }
}