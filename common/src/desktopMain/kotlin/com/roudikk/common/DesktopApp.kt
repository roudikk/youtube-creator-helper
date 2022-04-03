package com.roudikk.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.roudikk.common.cache.createDatabase

@Preview
@Composable
fun AppPreview() {
    YoutubeCreatorHelperApplication(createDatabase(com.roudikk.common.cache.DriverFactory()))
}