package com.jhonatanrojas.cartmovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CartMovieDao {
    @Query("SELECT * FROM cartMovie")
    fun getAll(): Observable<List<CartMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(cartMovie: CartMovie) : Completable
}