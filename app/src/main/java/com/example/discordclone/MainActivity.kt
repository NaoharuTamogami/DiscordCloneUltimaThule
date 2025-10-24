package com.example.discordclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.discordclone.ui.screens.ChannelsScreen
import com.example.discordclone.ui.screens.ChatScreen
import com.example.discordclone.ui.screens.LoginScreen
import com.example.discordclone.ui.screens.ServersScreen
import com.example.discordclone.ui.theme.DiscordCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscordCloneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiscordAppNavGraph()
                }
            }
        }
    }
}

@Composable
fun DiscordAppNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = "login") {

        composable(route = "login") {
            LoginScreen(
                viewModel = hiltViewModel(),
                onLogin = { userId ->
                    navController.navigate("servers/$userId") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "servers/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.LongType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
            ServersScreen(
                viewModel = hiltViewModel(),
                onServerClick = { serverId, serverName ->
                    navController.navigate("channels/$serverId/$serverName/$userId")
                }
            )
        }

        composable(
            route = "channels/{serverId}/{serverName}/{userId}",
            arguments = listOf(
                navArgument("serverId") { type = NavType.LongType },
                navArgument("serverName") { type = NavType.StringType },
                navArgument("userId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val serverName = backStackEntry.arguments?.getString("serverName") ?: "Canais"
            val userId = backStackEntry.arguments?.getLong("userId") ?: 0L

            ChannelsScreen(
                viewModel = hiltViewModel(),
                serverName = serverName,
                onChannelClick = { channelId, channelName ->
                    navController.navigate("chat/$channelId/$channelName/$userId")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "chat/{channelId}/{channelName}/{userId}",
            arguments = listOf(
                navArgument("channelId") { type = NavType.LongType },
                navArgument("channelName") { type = NavType.StringType },
                navArgument("userId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val channelName = backStackEntry.arguments?.getString("channelName") ?: "Chat"

            ChatScreen(
                viewModel = hiltViewModel(),
                channelName = channelName,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}