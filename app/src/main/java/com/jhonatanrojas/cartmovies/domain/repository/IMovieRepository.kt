package com.jhonatanrojas.cartmovies.domain.repository

import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import io.reactivex.Observable

interface IMovieRepository {
    fun getMovies(page: Int): Observable<MoviesResult>
    fun getMoviesDatabase(): Observable<List<Movie>>
    fun getMovieDatabaseById(id: Int): Observable<List<Movie>>
    fun updateMovieDB(movie: Movie)
    fun updateMovieAddCart(id: Int, isCart: Boolean)
    fun updateAllMovieDeleteCart(isCart: Boolean)
    fun insertMoviesDB(movie: Movie)
}