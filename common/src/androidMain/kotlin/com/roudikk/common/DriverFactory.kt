package com.roudikk.common

import android.content.Context
import com.roudikk.YoutubeCreatorHelperDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            YoutubeCreatorHelperDatabase.Schema,
            context, "youtube-creator-helper.db"
        )
    }
}
