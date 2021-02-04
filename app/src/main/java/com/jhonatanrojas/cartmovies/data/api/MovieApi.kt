package com.jhonatanrojas.cartmovies.data.api

import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import com.jhonatanrojas.cartmovies.data.services.MoviesApi
import io.reactivex.Observable
import javax.inject.Inject

class MovieApi @Inject constructor(private val moviesApi: MoviesApi) {
    private val API_KEY = "bf62e2a63e446390877307dc7d587025"
    private val ID_LIST = 1
    fun getMovies(page: Int): Observable<MoviesResult> {
        return moviesApi.getListMovies(
            idList = ID_LIST,
            page,
            apiKey = API_KEY
        )
    }
}