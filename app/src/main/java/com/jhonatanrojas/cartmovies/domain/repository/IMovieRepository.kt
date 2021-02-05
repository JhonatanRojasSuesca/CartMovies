package com.jhonatanrojas.cartmovies.domain.repository

import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import io.reactivex.Observable

interface IMovieRepository {
    fun getMovies(page: Int): Observable<MoviesResult>
    fun getMoviesDatabase(): Observable<List<Movie>>
    fun getMovieDatabaseById(id: Int): Observable<Movie>
    fun insertMoviesDB(movie: Movie)
}