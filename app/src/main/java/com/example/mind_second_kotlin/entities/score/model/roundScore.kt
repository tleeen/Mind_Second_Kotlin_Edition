
import androidx.compose.runtime.mutableStateOf

class RoundScore {
    private var roundScore =mutableStateOf(0);

    fun getRoundScore(): Int{
        return roundScore.value;
    }

    fun setRoundScore(score: Int) {
        roundScore.value = score
    }
}

object RoundScoreFactory {
    private var instance: RoundScore? = null

    fun createInstance():RoundScore {
        if (instance === null) {
            instance = RoundScore()
        }
        return instance!!
    }
}