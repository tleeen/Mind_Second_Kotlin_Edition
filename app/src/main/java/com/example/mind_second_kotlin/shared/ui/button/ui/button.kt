
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Button(action: () -> Unit, icon: ImageVector) {
  Button(
    onClick = { action() },
    shape = CircleShape,
    modifier = Modifier.size(88.dp),
    colors = ButtonDefaults.buttonColors(Color.Gray)
  ){
    Icon(
      icon,
      tint = Color.Black,
      contentDescription = "",
    )
  }
}