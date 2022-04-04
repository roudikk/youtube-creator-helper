import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.roudikk.common.YoutubeCreatorHelperApplication
import com.roudikk.common.cache.DriverFactory
import com.roudikk.common.cache.createDatabase
import com.roudikk.common.getPlatformName
import com.roudikk.common.ui.theme.YoutubeCreatorHelperTheme
import com.roudikk.common.widgets.imageResource

fun main() = application {
    val localDensity = LocalDensity.current

    val windowState = rememberWindowState()
    windowState.size = DpSize(1080.dp, 720.dp)
    windowState.position = WindowPosition(Alignment.Center)

    Window(
        onCloseRequest = ::exitApplication,
        title = "Youtube Creator Helper",
        undecorated = false,
        resizable = true,
        state = windowState,
        icon = imageResource("youtube_icon.png"),
    ) {
        Column(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
            YoutubeCreatorHelperTheme(false) {
                Column {
                    YoutubeCreatorHelperApplication(createDatabase(DriverFactory()))
                }
            }
        }
    }
}