package com.jhonatanrojas.cartmovies.di

import androidx.room.Room
import com.jhonatanrojas.cartmovies.data.local.MovieDao
import com.jhonatanrojas.cartmovies.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun providerDatabaseRoom(application: MoveApplication): MovieDatabase =
        Room.databaseBuilder(application, MovieDatabase::class.java, "movies_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providerDatabaseMovieDao(database: MovieDatabase) : MovieDao = database.movieDao()
}