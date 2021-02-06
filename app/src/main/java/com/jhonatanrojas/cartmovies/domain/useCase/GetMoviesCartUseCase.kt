package com.jhonatanrojas.cartmovies.domain.useCase

import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import io.reactivex.Observable

import javax.inject.Inject

class GetMoviesCartUseCase @Inject constructor(private val iCartMovieRepository: ICartMovieRepository) {

    fun getCartMoviesFromDatabase(): Observable<Result> {
        return iCartMovieRepository.getAllCart()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }
}