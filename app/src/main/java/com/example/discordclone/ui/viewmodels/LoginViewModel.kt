package com.example.discordclone.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discordclone.data.repository.DiscordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DiscordRepository
) : ViewModel() {

    fun loginOrRegisterUser(name: String, onLoginSuccess: (Long) -> Unit) {
        viewModelScope.launch {
            val userId = repository.loginOrRegisterUser(name)
            onLoginSuccess(userId)
        }
    }
}