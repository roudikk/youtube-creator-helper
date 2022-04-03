package com.roudikk.common.cache

import com.roudikk.YoutubeCreatorHelperDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual class DriverFactory {
  actual fun createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    YoutubeCreatorHelperDatabase.Schema.create(driver)
    return driver
  }
}