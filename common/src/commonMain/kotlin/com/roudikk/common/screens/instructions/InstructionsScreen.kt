package com.roudikk.common.screens.instructions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import cafe.adriel.voyager.core.screen.Screen
import com.roudikk.common.insets.paddingGutter
import com.roudikk.common.service.YoutubeApiRepository
import com.roudikk.common.widgets.imageResource
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
                .verticalScroll(scrollState)
                .padding(horizontal = paddingGutter(), vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val uriHandler = LocalUriHandler.current

            InstructionStep {
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
            }

            InstructionStep {
                Text(
                    text = "2. On the top blue bar nex to where it says \"Google Cloud Platform\", click the dropdown to 'Select a Project'." +
                            " (If you have existing projects, it may instead show the name of one)",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_2.png")
            }

            InstructionStep {
                Text(
                    text = "3. In the \"Select a Project\" Window click \"New Project\"",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_3.png")
            }

            InstructionStep {
                Text(
                    text = "4. Enter some project name, it doesn't really matter, then click 'Create'",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_4.png")
            }

            InstructionStep {
                Text(
                    text = "5. Make sure the project you just created is active, with it showing at the top," +
                            " then click \"Library\" on the left menu.",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_5.png")
            }

            InstructionStep {
                Text(
                    text = "6. Scroll down and click the box that says \"YouTube Data API v3\"",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_6.png")
            }

            InstructionStep {
                Text(
                    text = "7. Click \"Enable\" and wait for it to load. It will take you to another page.",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_7.png")
            }

            InstructionStep {
                Text(
                    text = """
                        8. Click "Create Credentials".
                        
                        (Note: If you don't see this page, open the left pop-out menu and click "APIs & Services" > "Dashboard". Then in the table/list on the page, look in the 'Name' column, and click "YouTube Data API v3")
                    """.trimIndent(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_8.png")
            }


            InstructionStep {
                Text(
                    text = "9. In the dropdown, select \"YouTube Data API v3\", then select \"User Data\", and click Next",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_9.png")
            }


            InstructionStep {
                Text(
                    text = "10. Under \"Oauth Consent Screen\", fill out the required fields with some name, select your email, and enter an email below too. It doesn't really matter what you put here. Then click \"Save and Continue\"",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_10.png")
            }

            InstructionStep {
                Text(
                    text = "11. Click \"Add or Remove Scopes\", then find the one that says \".../auth/youtube.force-ssl\", click the check box, then at the bottom click \"Update\". Then click \"Save and Continue\"",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_11_1.png")

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_11_2.png")
            }

            InstructionStep {
                Text(
                    text = "12. Under \"Oauth Client ID\", just select 'Desktop App'. You can set a name or not. Then click \"create\".",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(8.dp))

                InstructionImage("instructions_step_12.png")
            }

            InstructionStep {
                var text by remember { mutableStateOf("") }

                TextField(
                    modifier = Modifier.fillMaxWidth(),
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
}

@Composable
private fun InstructionImage(
    resourcePath: String
) {
    Image(
        modifier = Modifier
            .widthIn(max = 400.dp)
            .clip(RoundedCornerShape(4.dp)),
        painter = imageResource(resourcePath),
        contentDescription = null
    )
}

@Composable
private fun InstructionStep(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .widthIn(max = 600.dp, min = 600.dp)
            .padding(bottom = 16.dp),
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            content()
        }
    }
}