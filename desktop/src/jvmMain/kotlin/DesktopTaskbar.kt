import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DesktopTaskbar(
    title: String,
    onMinimize: () -> Unit,
    onExit: () -> Unit
) {
    Surface(
        modifier = Modifier.height(34.dp)
            .fillMaxWidth(),
        tonalElevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1F))

            TaskbarAction(
                color = Color(0xffbd954a),
                icon = Icons.Default.Remove,
                contentDescription = "Minimize",
                onClick = onMinimize
            )

            Spacer(modifier = Modifier.size(8.dp))

            TaskbarAction(
                color = Color(0xffab534d),
                icon = Icons.Default.Close,
                contentDescription = "Exit",
                onClick = onExit
            )

        }
    }
}

@Composable
fun TaskbarAction(
    color: Color,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(color)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}