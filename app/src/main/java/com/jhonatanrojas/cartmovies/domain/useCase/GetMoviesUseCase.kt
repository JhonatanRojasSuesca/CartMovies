package com.jhonatanrojas.cartmovies.domain.useCase

import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val iMovieRepository: IMovieRepository) {
    fun getMoviesFromApi(page: Int): Observable<Result> {
        return iMovieRepository.getMovies(page)
            .map { Result.Success(it.items) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }

    fun getMoviesFromDatabase(): Observable<Result> {
        return iMovieRepository.getMoviesDatabase()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)
    }

    fun getMovieFromDatabaseById(id: Int): Observable<Result> {
        return iMovieRepository.getMovieDatabaseById(id)
            .flatMap { it->
                if (it.size == 1){
                    Observable.just(Result.Success(it[0]))
                }else{
                   Observable.just(Result.Failure(Throwable("Something's wrong")))
                }
            }.startWith(Result.Loading)

    }
}