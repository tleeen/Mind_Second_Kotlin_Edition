
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@ExperimentalMaterial3Api
@Composable
fun Input(navController: NavHostController) {

    val message = remember{ mutableStateOf("") }

    Row() {
        TextField(
            value = message.value,
            onValueChange = { newText -> message.value = newText },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Введи ответ")},
            colors = TextFieldDefaults.textFieldColors(Color.Gray),
            textStyle = TextStyle(fontSize=25.sp)
        )
        Button(onClick = {
            analysisAnswer(message.value, navController)
            message.value = ""},
            colors = ButtonDefaults.buttonColors(Color.Gray)){
            Icon(
                Icons.Default.Check,
                tint = Color.Black,
                contentDescription = "",
            )

        }

    }
}
