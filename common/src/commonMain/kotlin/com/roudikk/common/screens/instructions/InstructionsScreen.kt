package com.roudikk.common.screens.instructions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.roudikk.common.insets.paddingGutter
import com.roudikk.common.service.YoutubeApiRepository
import org.kodein.di.compose.rememberInstance

class InstructionsScreen : Screen {

    @Composable
    override fun Content() {
        val repository by rememberInstance<YoutubeApiRepository>()
        val viewModel = remember { InstructionsViewModel(repository) }
        val state by viewModel.state.collectAsState()

        InstructionsContent(
            state = state,
            onUpdateSelected = viewModel::onUpdateSelected
        )
    }
}

@Composable
private fun InstructionsContent(
    state: InstructionsState,
    onUpdateSelected: (String) -> Unit
) {
    val scrollState = rememberScrollState()

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
                .padding(horizontal = paddingGutter())
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val uriHandler = LocalUriHandler.current

            Text(
                text = "1. Log into Google Developer's Console with your Google account that has your YouTube channel.",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(8.dp))

            Button(onClick = {
                uriHandler.openUri("https://console.cloud.google.com/apis/dashboard")
            }) {
                Text("Go to dashboard")
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = "2. On the top blue bar nex to where it says \"Google Cloud Platform\", click the dropdown to 'Select a Project'." +
                        " (If you have existing projects, it may instead show the name of one)",
                textAlign = TextAlign.Center
            )

            var text by remember { mutableStateOf("") }

            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Insert your key here") },
                isError = state.hasError
            )

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = { onUpdateSelected(text) },
                enabled = state.buttonEnabled
            ) {
                Text("Set API Key")
            }
        }
    }
}