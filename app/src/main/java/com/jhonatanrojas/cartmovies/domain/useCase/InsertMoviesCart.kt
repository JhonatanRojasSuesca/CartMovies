package com.jhonatanrojas.cartmovies.domain.useCase

import com.jhonatanrojas.cartmovies.core.utils.toCartMovie
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertMoviesCart @Inject constructor(private val iCartMovieRepository: ICartMovieRepository) {

    fun insertCartMovie(movie: Movie) : Completable {
        return iCartMovieRepository.insertMoviesCartDB(movie.toCartMovie())
    }
}