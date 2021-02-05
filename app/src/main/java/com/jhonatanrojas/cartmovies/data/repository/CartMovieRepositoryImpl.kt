package com.jhonatanrojas.cartmovies.data.repository

import com.jhonatanrojas.cartmovies.data.local.CartMovieDao
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import io.reactivex.Observable

class CartMovieRepositoryImpl(private val cartMovieDao: CartMovieDao) :
    ICartMovieRepository {

    override fun insertMoviesCartDB(movie: CartMovie)  {
        return cartMovieDao.insertMovies(movie)
    }

    override fun getAllCart(): Observable<List<CartMovie>> {
       return cartMovieDao.getAll()
    }

}