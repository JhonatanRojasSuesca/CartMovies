package com.jhonatanrojas.cartmovies.data.local

import androidx.room.*
import com.jhonatanrojas.cartmovies.data.models.Movie
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll(): Observable<List<Movie>>

    @Query("SELECT COUNT(id) FROM Movie")
    fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: Movie)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateMovies(movies: Movie)

    @Query("SELECT * FROM Movie where id = :id")
    fun getMovieById(id: Int): Observable<List<Movie>>

    @Query("UPDATE  Movie  SET cart= :isCart WHERE id = :id ")
    fun updateMovieAddCart(id: Int, isCart: Boolean)

    @Query("UPDATE  Movie  SET cart= :isCart")
    fun updateAllMovieDeleteCart(isCart: Boolean)

}