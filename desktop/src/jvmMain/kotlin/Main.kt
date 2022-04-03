import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

fun main() = application {
    val localDensity = LocalDensity.current

    val windowState = rememberWindowState()
    windowState.size = DpSize(1080.dp, 720.dp)
    windowState.position = WindowPosition(Alignment.Center)

    Window(
        onCloseRequest = ::exitApplication,
        title = "Youtube Creator Helper",
        undecorated = true,
        resizable = true,
        state = windowState,
        transparent = true,
    ) {
        Column(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
            YoutubeCreatorHelperTheme {
                Column {
                    DesktopTaskbar(
                        title = "Youtube Creator Helper (${getPlatformName()})",
                        onMinimize = { windowState.isMinimized = true },
                        onExit = ::exitApplication,
                        onDrag = { offset ->
                            println("Offset change: $offset")
                            windowState.position = with(localDensity) {
                                WindowPosition(
                                    x = windowState.position.x + offset.x.toDp(),
                                    y = windowState.position.y + offset.y.toDp()
                                )
                            }
                        }
                    )
                    YoutubeCreatorHelperApplication(createDatabase(DriverFactory()))
                }
            }
        }
    }
}