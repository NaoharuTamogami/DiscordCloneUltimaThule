package com.example.discordclone.data
data class User(val id: Int, val name: String)
data class Message(val author: User, val content: String)
data class Channel(val id: Int, val name: String, val messages: MutableList<Message>)
data class Server(val id: Int, val name: String, val channels: List<Channel>)

object FakeDatabase {
    private val user1 = User(1, "T.Kaczynski")
    private val user2 = User(2, "Camus")

    fun getServers(): List<Server> {
        return listOf(
            Server(1, "Servidor de Jogos", listOf(
                Channel(101, "# geral", mutableListOf(
                    Message(user1, "Olá, pessoal!"),
                    Message(user2, "E aí, tudo bem?")
                )),
                Channel(102, "# SchizoServer", mutableListOf(
                    Message(user1, "Bora jogar um HOI4?")
                ))
            )),
            Server(2, "Servidor de Estudo", listOf(
                Channel(201, "# android-dev", mutableListOf(
                    Message(user2, "Alguém me ajuda com Jetpack Compose?")
                )),
                Channel(202, "# dúvidas", mutableListOf())
            ))
        )
    }
}