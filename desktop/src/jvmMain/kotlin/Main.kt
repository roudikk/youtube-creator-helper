import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.roudikk.common.DriverFactory
import com.roudikk.common.YoutubeCreatorHelperApplication
import com.roudikk.common.createDatabase

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Youtube Creator Helper"
    ) {
        YoutubeCreatorHelperApplication(createDatabase(DriverFactory()))
    }
}