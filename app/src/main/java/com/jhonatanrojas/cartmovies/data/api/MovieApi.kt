package com.jhonatanrojas.cartmovies.data.api

import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import com.jhonatanrojas.cartmovies.data.services.MoviesApi
import io.reactivex.Observable
import javax.inject.Inject

class MovieApi @Inject constructor(private val moviesApi: MoviesApi) {
    fun getMovies(page: Int): Observable<MoviesResult> {
        return moviesApi.getListMovies(idList = 1, page, apiKey = "bf62e2a63e446390877307dc7d587025")
    }
}