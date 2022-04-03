package com.roudikk.common.screens.logout

import cafe.adriel.voyager.core.model.ScreenModel
import com.roudikk.common.service.YoutubeApiRepository

class ConfirmLogoutViewModel(
    private val repository: YoutubeApiRepository
) : ScreenModel {

    fun onLogoutSelected() {
        repository.clearApiKey()
    }
}