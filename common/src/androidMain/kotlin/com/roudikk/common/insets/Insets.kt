package com.roudikk.common.insets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ReadOnlyComposable
@Composable
actual fun paddingGutter(): Dp {
    val configuration = LocalConfiguration.current
    return if (configuration.screenWidthDp.dp > 600.dp) {
        56.dp
    } else {
        16.dp
    }
}