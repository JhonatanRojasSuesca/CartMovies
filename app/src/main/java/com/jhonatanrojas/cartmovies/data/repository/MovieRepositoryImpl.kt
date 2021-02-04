package com.jhonatanrojas.cartmovies.data.repository

import com.jhonatanrojas.cartmovies.data.api.MovieApi
import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import io.reactivex.Observable

class MovieRepositoryImpl(private val movieApi: MovieApi) : IMovieRepository{
    override fun getMovies(page: Int): Observable<MoviesResult> {
       return movieApi.getMovies(page)
    }
}