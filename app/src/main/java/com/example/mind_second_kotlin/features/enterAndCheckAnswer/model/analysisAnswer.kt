
import androidx.navigation.NavHostController
import com.example.mind_second_kotlin.entities.score.model.BestScoreFactory
import com.example.mind_second_kotlin.entities.timer.model.TimerFactory

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
                stateBestScore.setBestScore(stateRoundScore.getRoundScore())
            }
            navController.navigate("lose")
            timer.pause()
    }
}