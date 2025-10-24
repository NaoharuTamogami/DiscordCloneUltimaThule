package com.example.discordclone.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discordclone.data.local.entities.MessageEntity
import com.example.discordclone.data.repository.DiscordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.discordclone.data.local.relations.MessageWithAuthor

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: DiscordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val channelId: Long = checkNotNull(savedStateHandle["channelId"])


    private val currentUserId: Long = checkNotNull(savedStateHandle["userId"])


    val messages: StateFlow<List<MessageWithAuthor>> = repository.getMessagesForChannel(channelId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun sendMessage(content: String) {
        if (content.isBlank()) return

        viewModelScope.launch {
            val message = MessageEntity(
                content = content,
                ownerChannelId = channelId,
                authorId = currentUserId
            )
            repository.insertMessage(message)
        }
    }
}