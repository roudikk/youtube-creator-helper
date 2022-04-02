package com.roudikk.common

import com.roudikk.YoutubeCreatorHelperDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

data class MainState(
    val showHome: Boolean,
    val loading: Boolean
)

class YoutubeCreatorHelperApplicationViewModel(
    scope: CoroutineScope,
    database: YoutubeCreatorHelperDatabase
) {

    val stateFlow: StateFlow<MainState> = database.youtubeApiKeyQueries
        .select()
        .asFlow()
        .map { it.executeAsOneOrNull() }
        .map {
            println("Hello: $it")
            MainState(
                showHome = it?.isNotEmpty() == true,
                loading = false
            )
        }
        .stateIn(
            scope, SharingStarted.Eagerly, MainState(
                showHome = false,
                loading = true
            )
        )
}
