package com.example.discordclone.di

import android.content.Context
import androidx.room.Room
import com.example.discordclone.data.local.AppDatabase
import com.example.discordclone.data.local.dao.DiscordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "discord_clone.db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideDiscordDao(database: AppDatabase): DiscordDao {

        return database.discordDao()
    }
}