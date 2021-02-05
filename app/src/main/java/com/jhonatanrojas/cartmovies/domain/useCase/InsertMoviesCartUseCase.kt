package com.jhonatanrojas.cartmovies.domain.useCase

import com.jhonatanrojas.cartmovies.core.utils.toCartMovie
import com.jhonatanrojas.cartmovies.core.utils.toUpdateAddCart
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository

import javax.inject.Inject

class InsertMoviesCartUseCase @Inject constructor(private val iCartMovieRepository: ICartMovieRepository, private val iMovieRepository: IMovieRepository) {

    fun insertCartMovie(movie: Movie) {
        iMovieRepository.updateMovieDB(movie.toUpdateAddCart(true))
        return iCartMovieRepository.insertMoviesCartDB(movie.toCartMovie())
    }
}