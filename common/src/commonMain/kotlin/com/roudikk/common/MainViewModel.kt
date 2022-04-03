package com.roudikk.common

import com.roudikk.common.service.YoutubeApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

data class MainState(
    val showHome: Boolean,
    val loading: Boolean
)

class MainViewModel(
    coroutineScope: CoroutineScope,
    repository: YoutubeApiRepository
) {

    val stateFlow: StateFlow<MainState> = repository
        .flowOfApiKey()
        .map {
            MainState(
                showHome = it?.isNotEmpty() == true,
                loading = false
            )
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = MainState(
                showHome = false,
                loading = true
            )
        )
}
