package com.example.discordclone.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discordclone.data.local.entities.ServerEntity
import com.example.discordclone.data.repository.DiscordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServersViewModel @Inject constructor(
    private val repository: DiscordRepository
) : ViewModel() {


    val servers: StateFlow<List<ServerEntity>> = repository.getAllServers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    init {
        viewModelScope.launch {
            repository.prepopulateDatabase()
        }
    }
}