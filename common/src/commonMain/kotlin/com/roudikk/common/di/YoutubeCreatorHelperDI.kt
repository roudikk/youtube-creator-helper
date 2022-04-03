package com.roudikk.common.di

import androidx.compose.runtime.Composable
import com.roudikk.YoutubeCreatorHelperDatabase
import com.roudikk.common.MainViewModel
import com.roudikk.common.service.YoutubeApiRepository
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.compose.withDI
import org.kodein.di.instance

@Composable
fun YoutubeCreatorHelperDI(
    coroutineScope: CoroutineScope,
    database: YoutubeCreatorHelperDatabase,
    content: @Composable () -> Unit
) = withDI(
    di = DI {
        bindSingleton { YoutubeApiRepository(database) }
        bindSingleton {
            MainViewModel(
                coroutineScope = coroutineScope,
                repository = instance()
            )
        }
    },
    content = content
)
