package com.example.discordclone.data.repository

import com.example.discordclone.data.local.dao.DiscordDao
import com.example.discordclone.data.local.entities.ChannelEntity
import com.example.discordclone.data.local.entities.MessageEntity
import com.example.discordclone.data.local.entities.ServerEntity
import com.example.discordclone.data.local.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

import com.example.discordclone.data.local.relations.MessageWithAuthor

@Singleton class DiscordRepository @Inject constructor(
    private val dao: DiscordDao ) {


    fun getAllServers(): Flow<List<ServerEntity>> {
        return dao.getAllServers()
    }

    fun getChannelsForServer(serverId: Long): Flow<List<ChannelEntity>> {
        return dao.getChannelsForServer(serverId)
    }

    fun getMessagesForChannel(channelId: Long): Flow<List<MessageWithAuthor>> {
        return dao.getMessagesForChannel(channelId)
    }

    suspend fun getUserById(userId: Long): UserEntity? {
        return dao.getUserById(userId)
    }


    suspend fun insertMessage(message: MessageEntity) {
        withContext(Dispatchers.IO) {
            dao.insertMessage(message)
        }
    }

    suspend fun loginOrRegisterUser(name: String): Long {
        return withContext(Dispatchers.IO) {
            var user = dao.getUserByName(name)
            if (user == null) {
                user = UserEntity(name = name)
                return@withContext dao.insertUser(user)             }
            return@withContext user.userId
        }
    }


    suspend fun prepopulateDatabase() {
        withContext(Dispatchers.IO) {
            if (dao.getServerCount() > 0) return@withContext


            val user1Id = dao.insertUser(UserEntity(name = "Fulano"))
            val user2Id = dao.insertUser(UserEntity(name = "Ciclano"))


            val server1Id = dao.insertServer(ServerEntity(name = "Servidor de Jogos"))
            val server2Id = dao.insertServer(ServerEntity(name = "Servidor de Estudo"))


            val channel1Id = dao.insertChannel(ChannelEntity(name = "geral", ownerServerId = server1Id))
            val channel2Id = dao.insertChannel(ChannelEntity(name = "valorant", ownerServerId = server1Id))
            val channel3Id = dao.insertChannel(ChannelEntity(name = "android-dev", ownerServerId = server2Id))


            dao.insertMessage(MessageEntity(content = "Olá, pessoal!", ownerChannelId = channel1Id, authorId = user1Id))
            dao.insertMessage(MessageEntity(content = "E aí, tudo bem?", ownerChannelId = channel1Id, authorId = user2Id))
            dao.insertMessage(MessageEntity(content = "Bora jogar um Spiehl des Jahres?", ownerChannelId = channel2Id, authorId = user1Id))
            dao.insertMessage(MessageEntity(content = "Alguém me ajuda com Jetpack Compose?", ownerChannelId = channel3Id, authorId = user2Id))
        }
    }
}