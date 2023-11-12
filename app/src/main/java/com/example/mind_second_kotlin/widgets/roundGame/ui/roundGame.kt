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
    val Task = TaskFactory.createInstance()
    val task = Task.getTask()

    val FunctionalTimer = TimerFactory.createInstance()
    val timer =  FunctionalTimer.getTimerStr()
    val persent =  FunctionalTimer.getPercent()

    LaunchedEffect(Unit) {
        Task.createTask()

        val stateBestScore = BestScoreFactory.createInstance()
        val stateRoundScore = RoundScoreFactory.createInstance()
        stateBestScore.setBestScore(RepositoryScore.getScore())
        stateRoundScore.setRoundScore(0)

        FunctionalTimer.setCallback {if (stateRoundScore.getRoundScore() > stateBestScore.getBestScore()) {
            RepositoryScore.setScore(stateRoundScore.getRoundScore())
            stateBestScore.setBestScore(stateRoundScore.getRoundScore())
        }
            navController.navigate("lose")}
        FunctionalTimer.start()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Timer(persent, timer)
        Text(task, fontSize = 30.sp)
        Input(navController)
    }
}
