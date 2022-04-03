package com.roudikk.common.screens.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.roudikk.common.ScreenEvent
import com.roudikk.common.screens.logout.ConfirmLogoutScreen
import com.roudikk.common.service.YoutubeApiRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.kodein.di.compose.rememberInstance

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalBottomSheetNavigator.current
        val repository by rememberInstance<YoutubeApiRepository>()
        val viewModel = rememberScreenModel { HomeViewModel(repository) }
        val state by viewModel.state.collectAsState()

        HomeContent(
            onLogoutSelected = viewModel::onLogoutSelected,
            state = state
        )

        LaunchedEffect(Unit) {
            viewModel.commandsFlow
                .onEach { command ->
                    when (command) {
                        HomeCommand.ShowConfirmLogoutDialog -> navigator.show(ConfirmLogoutScreen())
                    }
                }
                .launchIn(this)
        }
    }
}

@Composable
private fun HomeContent(
    onLogoutSelected: ScreenEvent,
    state: HomeState
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Home") },
                actions = {
                    TextButton(onClick = onLogoutSelected) {
                        Text(text = "Logout")
                    }
                }
            )
        },
    ) {

    }
}
