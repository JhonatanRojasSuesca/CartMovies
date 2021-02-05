package com.jhonatanrojas.cartmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.data.models.Movie

@Database(entities = [Movie::class,CartMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun cartMovieDao(): CartMovieDao
}