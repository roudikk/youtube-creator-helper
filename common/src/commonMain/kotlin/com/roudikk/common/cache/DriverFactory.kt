package com.roudikk.common.cache

import com.roudikk.YoutubeCreatorHelperDatabase
import com.squareup.sqldelight.db.SqlDriver
expect class DriverFactory {
    fun createDriver(): SqlDriver
}
fun createDatabase(driverFactory: DriverFactory): YoutubeCreatorHelperDatabase {
    val driver = driverFactory.createDriver()
    return YoutubeCreatorHelperDatabase(driver)
}