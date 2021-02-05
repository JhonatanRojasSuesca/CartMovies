package com.jhonatanrojas.cartmovies.domain.useCase

import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository

import javax.inject.Inject

class DeleteMoviesCartUseCase @Inject constructor(private val iCartMovieRepository: ICartMovieRepository, private val iMovieRepository: IMovieRepository) {

    fun deleteCartMovie(movie: CartMovie) {
        iMovieRepository.updateMovieAddCart(movie.id, false)
        iCartMovieRepository.deleteCartMovie(movie)
    }

    fun deleteAllCartMovie() {
        iMovieRepository.updateAllMovieDeleteCart(false)
        iCartMovieRepository.deleteAllCartMovie()
    }
}