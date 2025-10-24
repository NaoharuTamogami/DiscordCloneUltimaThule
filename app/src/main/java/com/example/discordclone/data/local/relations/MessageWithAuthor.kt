package com.example.discordclone.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.discordclone.data.local.entities.MessageEntity
import com.example.discordclone.data.local.entities.UserEntity

data class MessageWithAuthor(
    @Embedded
    val message: MessageEntity,

    @Relation(
        parentColumn = "authorId",
        entityColumn = "userId"
    )
    val author: UserEntity?
)