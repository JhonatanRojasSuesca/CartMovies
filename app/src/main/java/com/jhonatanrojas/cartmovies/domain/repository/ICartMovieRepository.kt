package com.jhonatanrojas.cartmovies.domain.repository

import com.jhonatanrojas.cartmovies.data.models.CartMovie
import io.reactivex.Completable

interface ICartMovieRepository {
    fun insertMoviesCartDB(movie: CartMovie) : Completable
}