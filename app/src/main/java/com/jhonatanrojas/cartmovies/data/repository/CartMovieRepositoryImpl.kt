package com.jhonatanrojas.cartmovies.data.repository

import com.jhonatanrojas.cartmovies.data.local.CartMovieDao
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import io.reactivex.Completable
import io.reactivex.subjects.PublishSubject

class CartMovieRepositoryImpl(private val cartMovieDao: CartMovieDao) :
    ICartMovieRepository {

    override fun insertMoviesCartDB(movie: CartMovie) : Completable {
        return cartMovieDao.insertMovies(movie)

    }

}