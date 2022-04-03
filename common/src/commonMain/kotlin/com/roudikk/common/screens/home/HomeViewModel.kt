package com.roudikk.common.screens.home

import cafe.adriel.voyager.core.model.StateScreenModel
import com.roudikk.common.service.YoutubeApiRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class HomeState

sealed class HomeCommand {
    object ShowConfirmLogoutDialog : HomeCommand()
}

class HomeViewModel(
    private val repository: YoutubeApiRepository
) : StateScreenModel<HomeState>(HomeState()) {

    private val mutableCommandsFlow = MutableSharedFlow<HomeCommand>(extraBufferCapacity = 1)
    val commandsFlow = mutableCommandsFlow.asSharedFlow()

    fun onLogoutSelected() {
        mutableCommandsFlow.tryEmit(HomeCommand.ShowConfirmLogoutDialog)
    }
}
