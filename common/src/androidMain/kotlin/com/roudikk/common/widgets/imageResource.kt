package com.roudikk.common.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
actual fun imageResource(resourcePath: String): Painter {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(resourcePath, "drawable", context.packageName)
    return painterResource(id)
}