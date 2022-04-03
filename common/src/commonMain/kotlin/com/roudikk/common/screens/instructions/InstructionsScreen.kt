package com.roudikk.common.screens.instructions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
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
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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