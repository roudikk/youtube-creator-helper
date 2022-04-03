package com.roudikk.common.screens.logout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.roudikk.common.ScreenEvent
import com.roudikk.common.service.YoutubeApiRepository
import org.kodein.di.compose.rememberInstance

class ConfirmLogoutScreen : Screen {

    @Composable
    override fun Content() {
        val repository by rememberInstance<YoutubeApiRepository>()
        val viewModel = rememberScreenModel { ConfirmLogoutViewModel(repository) }

        ConfirmLogoutContent(
            onLogoutSelected = viewModel::onLogoutSelected
        )
    }
}

@Composable
private fun ConfirmLogoutContent(
    onLogoutSelected: ScreenEvent
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = """
            By logging out, the stored Youtube API key will be cleared, all active jobs will also be cancelled.
            
            Are you sure you want to logout?
        """.trimIndent()
        )

        Spacer(Modifier.size(16.dp))

        Button(
            onClick = onLogoutSelected,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Logout")
        }
    }
}
