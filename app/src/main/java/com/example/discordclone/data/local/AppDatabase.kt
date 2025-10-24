package com.example.discordclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.discordclone.data.local.dao.DiscordDao
import com.example.discordclone.data.local.entities.ChannelEntity
import com.example.discordclone.data.local.entities.MessageEntity
import com.example.discordclone.data.local.entities.ServerEntity
import com.example.discordclone.data.local.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ServerEntity::class,
        ChannelEntity::class,
        MessageEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun discordDao(): DiscordDao


}