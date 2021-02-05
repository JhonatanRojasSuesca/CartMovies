package com.jhonatanrojas.cartmovies.domain.useCase

import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository

import javax.inject.Inject

class DeleteMoviesCartUseCase @Inject constructor(private val iCartMovieRepository: ICartMovieRepository, private val iMovieRepository: IMovieRepository) {

    fun deleteCartMovie(movie: CartMovie) {
        return iCartMovieRepository.deleteCartMovie(movie)
    }

    fun deleteAllCartMovie() {
        return iCartMovieRepository.deleteAllCartMovie()
    }
}