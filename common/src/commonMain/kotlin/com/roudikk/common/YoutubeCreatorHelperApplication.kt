package com.roudikk.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.roudikk.YoutubeCreatorHelperDatabase
import com.roudikk.common.di.YoutubeCreatorHelperDI
import com.roudikk.common.screens.instructions.InstructionsScreen
import com.roudikk.common.screens.HomeScreen
import com.roudikk.common.ui.theme.YoutubeCreatorHelperTheme
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

val LocalDatabase = staticCompositionLocalOf<YoutubeCreatorHelperDatabase> { error("Must be provided.") }

@Composable
fun YoutubeCreatorHelperApplication(
    database: YoutubeCreatorHelperDatabase
) = withDI(
    YoutubeCreatorHelperDI(
        coroutineScope = rememberCoroutineScope(),
        database = database
    )
) {
    YoutubeCreatorHelperTheme {
        val applicationViewModel by rememberInstance<YoutubeCreatorHelperApplicationViewModel>()

        val state by applicationViewModel.stateFlow.collectAsState()

        if (state.loading) return@YoutubeCreatorHelperTheme

        Surface(modifier = Modifier.fillMaxSize()) {
            Crossfade(
                targetState = state.showHome
            ) { targetState ->
                if (targetState) {
                    Navigator(HomeScreen())
                } else {
                    InstructionsScreen().Content()
                }
            }
        }
    }
}
