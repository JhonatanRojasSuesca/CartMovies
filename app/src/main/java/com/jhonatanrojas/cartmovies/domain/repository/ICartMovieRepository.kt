package com.jhonatanrojas.cartmovies.domain.repository

import com.jhonatanrojas.cartmovies.data.models.CartMovie
import io.reactivex.Observable

interface ICartMovieRepository {
    fun insertMoviesCartDB(movie: CartMovie)
    fun deleteCartMovie(movie: CartMovie)
    fun deleteAllCartMovie()
    fun getAllCart(): Observable<List<CartMovie>>
}