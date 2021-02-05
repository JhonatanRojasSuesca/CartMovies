package com.jhonatanrojas.cartmovies.data.repository

import com.jhonatanrojas.cartmovies.data.local.CartMovieDao
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository

class CartMovieRepositoryImpl(private val cartMovieDao: CartMovieDao) :
    ICartMovieRepository {

    override fun insertMoviesCartDB(movie: CartMovie)  {
        return cartMovieDao.insertMovies(movie)
    }

}