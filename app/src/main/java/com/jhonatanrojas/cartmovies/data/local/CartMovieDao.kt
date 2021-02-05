package com.jhonatanrojas.cartmovies.data.local

import androidx.room.*
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import io.reactivex.Observable

@Dao
interface CartMovieDao {
    @Query("SELECT * FROM cartMovie")
    fun getAll(): Observable<List<CartMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(cartMovie: CartMovie)

    @Delete
    fun deleteCartMovie(cartMovie: CartMovie)

    @Query("DELETE FROM cartMovie")
    fun deleteAllCartMovie()
}