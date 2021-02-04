package com.jhonatanrojas.cartmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jhonatanrojas.cartmovies.data.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}