package com.example.discordclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.discordclone.data.local.entities.ServerEntity
import com.example.discordclone.ui.viewmodels.ServersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServersScreen(
    viewModel: ServersViewModel,
    onServerClick: (Long, String) -> Unit
) {

    val servers by viewModel.servers.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Servidores") }) }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            items(servers) { server ->
                ServerListItem(
                    server = server,
                    onClick = {

                        onServerClick(server.serverId, server.name)
                    }
                )
            }
        }
    }
}

@Composable
fun ServerListItem(server: ServerEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ){
                Text(text = server.name.take(1), color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = server.name, fontSize = 20.sp)
        }
    }
}