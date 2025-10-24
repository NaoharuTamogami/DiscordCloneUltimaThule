package com.example.discordclone.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",

    foreignKeys = [
        ForeignKey(
            entity = ChannelEntity::class,
            parentColumns = ["channelId"],
            childColumns = ["ownerChannelId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["authorId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("ownerChannelId"), Index("authorId")]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val messageId: Long = 0,
    val content: String,
    val ownerChannelId: Long,
    val authorId: Long? 
)