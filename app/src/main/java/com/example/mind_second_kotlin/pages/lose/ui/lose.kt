

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.entities.score.model.BestScoreFactory

@Composable
fun LoseScreen(navController: NavHostController) {

    val bestScore = BestScoreFactory.createInstance()
    val bestScoreValue: String = bestScore.getBestScore().toString()

    val roundScore = RoundScoreFactory.createInstance()
    val roundScoreValue: String = roundScore.getRoundScore().toString()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (bestScoreValue != roundScoreValue) {
            Text(
                text = "Счёт: $roundScoreValue",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
                , fontSize = 30.sp
            )
            Text(
                text = "Рекорд: $bestScoreValue",
                textAlign = TextAlign.Center
                , fontSize = 30.sp
            )
        }
        else{
            Text(
                text = "Новый рекорд: $bestScoreValue!",
                textAlign = TextAlign.Center
                , fontSize = 30.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        Button(
            action = { navController.navigate("start") },
            icon = Icons.Filled.Home,
        )
        Button(
            action = { navController.navigate("game") },
            icon = Icons.Filled.Refresh,
        )
        }
    }
}
