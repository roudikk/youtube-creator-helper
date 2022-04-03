package com.roudikk.common.screens.instructions

import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.StateScreenModel
import com.roudikk.common.service.YoutubeApiRepository

@Immutable
data class InstructionsState(
    val hasError: Boolean,
    val buttonEnabled: Boolean
)
class InstructionsViewModel(
    private val repository: YoutubeApiRepository
) : StateScreenModel<InstructionsState>(
    InstructionsState(
        hasError = false,
        buttonEnabled = true
    )
) {

    fun onUpdateSelected(text: String) {
        if (text.isBlank()) {
            mutableState.value = InstructionsState(hasError = true, buttonEnabled = true)
            return
        }
        mutableState.value = InstructionsState(hasError = false, buttonEnabled = false)
        repository.updateApikey(text)
    }
}
