package com.jhonatanrojas.cartmovies.data.services

import com.jhonatanrojas.cartmovies.data.models.MoviesResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("list/{idList}")
    fun getListMovies(
        @Path("idList") idList: Int,
        @Query("page") numberPath: Int,
        @Query("api_key") apiKey:String): Observable<MoviesResult>
}