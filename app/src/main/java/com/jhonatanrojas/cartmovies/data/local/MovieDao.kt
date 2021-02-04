package com.jhonatanrojas.cartmovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("SELECT * FROM Movie where id = :id")
    fun getMovieById(id: Int) : Observable<Movie>

}