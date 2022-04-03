package com.roudikk.common.di

import androidx.compose.runtime.Composable
import com.roudikk.YoutubeCreatorHelperDatabase
import com.roudikk.common.YoutubeCreatorHelperApplicationViewModel
import com.roudikk.common.service.YoutubeApiRepository
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

@Composable
fun YoutubeCreatorHelperDI(
    coroutineScope: CoroutineScope,
    database: YoutubeCreatorHelperDatabase
) = DI {
    bindSingleton { YoutubeApiRepository(database) }
    bindSingleton {
        YoutubeCreatorHelperApplicationViewModel(
            coroutineScope = coroutineScope,
            repository = instance()
        )
    }
}
