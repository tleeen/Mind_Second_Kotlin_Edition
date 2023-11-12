import android.content.Context
import android.content.SharedPreferences

object RepositoryScore {
  private lateinit var preference: SharedPreferences

  fun initialize(context: Context) {
    preference = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
  }

  fun setScore(value: Int) {
    val editor = preference.edit()
    editor.putInt("bestScore", value)
    editor.apply()
  }

  fun getScore(): Int {
    return preference.getInt("bestScore", 0)
  }
}