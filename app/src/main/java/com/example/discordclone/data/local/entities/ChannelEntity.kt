package com.example.discordclone.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "channels",

    foreignKeys = [
        ForeignKey(
            entity = ServerEntity::class,
            parentColumns = ["serverId"],
            childColumns = ["ownerServerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ownerServerId")]
)
data class ChannelEntity(
    @PrimaryKey(autoGenerate = true)
    val channelId: Long = 0,
    val name: String,
    val ownerServerId: Long
)