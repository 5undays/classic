package com.cinema.classic.di

import android.app.Application
import androidx.room.Room
import com.cinema.classic.data.local.MovieClipDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): MovieClipDatabase {
        return Room.databaseBuilder(
            app,
            MovieClipDatabase::class.java,
            MovieClipDatabase.DATABASE_NAME
        ).build()
    }


}