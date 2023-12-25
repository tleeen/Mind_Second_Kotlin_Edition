
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.entities.score.model.BestScoreFactory
import com.example.mind_second_kotlin.entities.score.model.RoundScoreFactory
import com.example.mind_second_kotlin.entities.task.model.task.TaskFactory
import com.example.mind_second_kotlin.entities.timer.model.TimerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun analysisAnswer(answer: String, navController: NavHostController) {
    val task = TaskFactory.createInstance()
    val timer = TimerFactory.createInstance()
    val stateBestScore = BestScoreFactory.createInstance()
    val stateRoundScore = RoundScoreFactory.createInstance()

    if (answer == task.getAnswerForTask()) {
        task.createTask()
        stateRoundScore.setRoundScore(stateRoundScore.getRoundScore() + 1)
        timer.restart()
    } else {
            if (stateRoundScore.getRoundScore() > stateBestScore.getBestScore()) {
                CoroutineScope(Dispatchers.IO).launch {
                    stateBestScore.setBestScore(stateRoundScore.getRoundScore())
                }
            }
            navController.navigate("lose")
            timer.pause()
    }
}