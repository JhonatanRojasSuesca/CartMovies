package com.jhonatanrojas.cartmovies.domain.repository

import com.jhonatanrojas.cartmovies.data.models.CartMovie

interface ICartMovieRepository {
    fun insertMoviesCartDB(movie: CartMovie)
}