
import androidx.compose.runtime.mutableStateOf
import com.example.mind_second_kotlin.entities.task.model.operations._interface.Operation
import com.example.mind_second_kotlin.entities.task.model.operations.implementation.Addition
import com.example.mind_second_kotlin.entities.task.model.operations.implementation.Division
import com.example.mind_second_kotlin.entities.task.model.operations.implementation.Multiplication
import com.example.mind_second_kotlin.entities.task.model.operations.implementation.Subtraction
import kotlin.random.Random

class Task {
    private var task = mutableStateOf("")
    private var answerForTask = mutableStateOf("")

    init {
        createTask()
    }

    fun getTask(): String {
        return task.value;
    }

    fun getAnswerForTask(): String {
        return answerForTask.value;
    }

    fun createTask() {
        val operations = listOf("+", "-", "/", "*")
        val operation: Operation = when (operations[Random.nextInt(operations.size)]) {
            "+" -> Addition()
            "-" -> Subtraction()
            "/" -> Division()
            "*" -> Multiplication()
            else -> Addition()
        }
        answerForTask.value = operation.getAnswerTaskOperation()
        task.value = operation.getTaskOperation()
    }
}

object TaskFactory {
    private var instance: Task? = null

    fun createInstance(): Task {
        if (instance === null) {
            instance = Task()
        }
        return instance!!
    }
}

