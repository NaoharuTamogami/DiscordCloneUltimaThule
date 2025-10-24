package com.example.discordclone.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers")
data class ServerEntity(
    @PrimaryKey(autoGenerate = true)
    val serverId: Long = 0,
    val name: String
)