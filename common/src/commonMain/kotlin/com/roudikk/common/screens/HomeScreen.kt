package com.roudikk.common.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
class HomeScreen: Screen {

    @Composable
    override fun Content() {
        Text("Home!")
    }
}