package com.roudikk.common.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun imageResource(resourcePath: String): Painter {
    return painterResource(resourcePath)
}