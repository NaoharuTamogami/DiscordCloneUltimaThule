package com.example.discordclone.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discordclone.data.local.entities.ChannelEntity
import com.example.discordclone.data.repository.DiscordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChannelsViewModel @Inject constructor(
    repository: DiscordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val serverId: Long = checkNotNull(savedStateHandle["serverId"])

    val channels: StateFlow<List<ChannelEntity>> = repository.getChannelsForServer(serverId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}