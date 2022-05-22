package com.muhammadhusniabdillah.themoviedb.di

import android.content.Context
import androidx.room.Room
import com.muhammadhusniabdillah.themoviedb.data.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun databaseProvider(
        @ApplicationContext context: Context
    ): MoviesDatabase = Room.databaseBuilder(
        context,
        MoviesDatabase::class.java,
        "movies.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun userDaoProvider(database: MoviesDatabase) = database.userDao()

}