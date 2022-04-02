package com.roudikk.android

import com.roudikk.common.App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.roudikk.common.DriverFactory
import com.roudikk.common.YoutubeCreatorHelperApplication
import com.roudikk.common.createDatabase
import com.roudikk.common.ui.theme.YoutubeCreatorHelperTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val database = createDatabase(DriverFactory(this))

        setContent {
            val systemUiController = rememberSystemUiController()

            systemUiController.setNavigationBarColor(
                color = Color.Transparent,
                darkIcons = !isSystemInDarkTheme(),
                navigationBarContrastEnforced = false
            )

            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = !isSystemInDarkTheme(),
            )

            ProvideWindowInsets {
                YoutubeCreatorHelperTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .statusBarsPadding()
                                .navigationBarsWithImePadding()
                        ) {
                            YoutubeCreatorHelperApplication(database)
                        }
                    }
                }
            }
        }
    }
}