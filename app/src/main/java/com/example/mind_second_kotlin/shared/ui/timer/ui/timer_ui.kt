package com.example.mind_second_kotlin.shared.ui.timer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Timer(percent: Double, time: String) {
    Box( modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center ) {
        CircularProgressIndicator(
            progress = (percent).toFloat(),
            modifier = Modifier.size(170.dp),
            color = Color.Gray,
            strokeWidth = 8.dp,
        )
        Text(text = time, fontSize = 30.sp)
    }
}
