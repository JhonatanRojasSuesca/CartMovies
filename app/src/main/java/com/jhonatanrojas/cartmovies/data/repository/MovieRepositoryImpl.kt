package com.jhonatanrojas.cartmovies.data.repository

import com.jhonatanrojas.cartmovies.data.api.MovieApi
import com.jhonatanrojas.cartmovies.data.local.MovieDao
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import io.reactivex.Observable

class MovieRepositoryImpl(private val movieApi: MovieApi, private val movieDao: MovieDao) :
    IMovieRepository {
    override fun getMovies(page: Int): Observable<MoviesResult> {
        return movieApi.getMovies(page).doOnNext { it ->
            it.items.forEach { movie -> insertMoviesDB(movie) }
        }
    }

    override fun getMoviesDatabase(): Observable<List<Movie>> {
        return movieDao.getAll()
    }

    override fun getMovieDatabaseById(id: Int): Observable<Movie> {
        return movieDao.getMovieById(id)
    }

    override fun insertMoviesDB(movie: Movie) {
        movieDao.insertMovies(movie)
    }

}