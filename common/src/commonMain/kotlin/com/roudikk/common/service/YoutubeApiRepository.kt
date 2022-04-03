package com.roudikk.common.service

import com.roudikk.YoutubeCreatorHelperDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class YoutubeApiRepository(
    private val database: YoutubeCreatorHelperDatabase
) {

    fun currentApiKey(): String? = database.youtubeApiKeyQueries.select().executeAsOneOrNull()

    fun flowOfApiKey(): Flow<String?> = database.youtubeApiKeyQueries
        .select()
        .asFlow()
        .map { it.executeAsOneOrNull() }

    fun updateApikey(apiKey: String) {
        database.youtubeApiKeyQueries.insert(apiKey)
    }

    fun clearApiKey() {
        database.youtubeApiKeyQueries.clear()
    }
}