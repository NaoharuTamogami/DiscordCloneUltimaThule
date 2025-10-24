package com.example.discordclone.data.local.dao
import com.example.discordclone.data.local.relations.MessageWithAuthor

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.discordclone.data.local.entities.ChannelEntity
import com.example.discordclone.data.local.entities.MessageEntity
import com.example.discordclone.data.local.entities.ServerEntity
import com.example.discordclone.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServer(server: ServerEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: ChannelEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM servers")
    fun getAllServers(): Flow<List<ServerEntity>>

    @Query("SELECT * FROM channels WHERE ownerServerId = :serverId")
    fun getChannelsForServer(serverId: Long): Flow<List<ChannelEntity>>


    @Transaction
    @Query("SELECT * FROM messages WHERE ownerChannelId = :channelId ORDER BY messageId ASC")
    fun getMessagesForChannel(channelId: Long): Flow<List<MessageWithAuthor>>

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: Long): UserEntity?

    @Query("SELECT * FROM users WHERE name = :name LIMIT 1")
    suspend fun getUserByName(name: String): UserEntity?

    @Query("SELECT COUNT(serverId) FROM servers")
    suspend fun getServerCount(): Int
}