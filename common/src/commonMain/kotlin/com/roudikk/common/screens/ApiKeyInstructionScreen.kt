package com.roudikk.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.roudikk.common.LocalDatabase

class ApiKeyInstructionScreen : Screen {
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val scrollState = rememberScrollState()
        val database = LocalDatabase.current

        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Youtube Api Key Instructions") }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var text by remember { mutableStateOf("") }

                TextField(
                    value = text,
                    onValueChange = { text = it }
                )

                Spacer(modifier = Modifier.size(16.dp))

                Button(onClick = {
                    println("Clicking!!")
                    database.youtubeApiKeyQueries.insert("Test")
                }) {
                    Text("Set API Key")
                }
            }

        }
    }
}