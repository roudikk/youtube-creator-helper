package com.roudikk.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.roudikk.YoutubeCreatorHelperDatabase
import com.roudikk.common.di.YoutubeCreatorHelperDI
import com.roudikk.common.screens.instructions.InstructionsScreen
import com.roudikk.common.screens.home.HomeScreen
import org.kodein.di.compose.rememberInstance

@Composable
fun YoutubeCreatorHelperApplication(
    database: YoutubeCreatorHelperDatabase
) = YoutubeCreatorHelperDI(
    coroutineScope = rememberCoroutineScope(),
    database = database
) {
    val applicationViewModel by rememberInstance<MainViewModel>()

    val state by applicationViewModel.stateFlow.collectAsState()

    if (state.loading) return@YoutubeCreatorHelperDI

    Surface(modifier = Modifier.fillMaxSize()) {
        Crossfade(
            targetState = state.showHome
        ) { targetState ->
            if (targetState) {
                ApplicationNavigation()
            } else {
                InstructionsScreen().Content()
            }
        }
    }
}

@Composable
private fun ApplicationNavigation() {
    BottomSheetNavigator(
        scrimColor = Color.Black.copy(alpha = 0.2F),
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    ) {
        Navigator(HomeScreen())
    }
}