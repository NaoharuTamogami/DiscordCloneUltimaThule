package com.example.discordclone.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.discordclone.data.local.entities.ChannelEntity
import com.example.discordclone.ui.viewmodels.ChannelsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelsScreen(
    viewModel: ChannelsViewModel,
    serverName: String,
    onChannelClick: (Long, String) -> Unit,
    onBackClick: () -> Unit
) {

    val channels by viewModel.channels.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(serverName) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(channels) { channel ->
                ChannelListItem(
                    channel = channel,
                    onClick = {
                        onChannelClick(channel.channelId, channel.name)
                    }
                )
            }
        }
    }
}

@Composable
fun ChannelListItem(channel: ChannelEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Info, contentDescription = "Canal")
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "# ${channel.name}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}