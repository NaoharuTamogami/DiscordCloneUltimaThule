package com.example.discordclone.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.discordclone.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLogin: (Long) -> Unit
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nome de usuário") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (username.isNotBlank()) {
                Toast.makeText(context, "Logando...", Toast.LENGTH_SHORT).show()

                viewModel.loginOrRegisterUser(username, onLogin)
            } else {
                Toast.makeText(context, "Por favor, digite um nome.", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Entrar")
        }
    }
}