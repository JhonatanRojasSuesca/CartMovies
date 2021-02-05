package com.jhonatanrojas.cartmovies.ui.viewmodel.detail

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.core.utils.addTo
import com.jhonatanrojas.cartmovies.core.utils.toCartMovie
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.useCase.DeleteMoviesCartUseCase
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesUseCase
import com.jhonatanrojas.cartmovies.domain.useCase.InsertMoviesCartUseCase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase, private val insertMoviesCartUseCase: InsertMoviesCartUseCase, private val deleteMoviesCartUseCase: DeleteMoviesCartUseCase) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    var movie = ObservableField<Movie>()
    fun getMovieById(id: Int) {
        getMoviesUseCase.getMovieFromDatabaseById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handlerMoviesDatabaseResult(it) }.addTo(compositeDisposable)
    }

    private fun handlerMoviesDatabaseResult(result: Result) {
        when (result) {
            is Result.Success -> {
                movie.set(result.data as Movie)
                Log.e("mainviewModel", "mensaje Detalle ${(result.data) as Movie}")
            }
            is Result.Failure -> {
                Log.e("mainviewModel", "mensaje incorrecto ${result.throwable}")
            }
            Result.Loading -> {
                Log.e("mainviewModel", "mensaje Loading ")
            }
        }
    }

    fun insertMovieCart(movie: Movie) {
        Completable.fromAction {
            insertMoviesCartUseCase.insertCartMovie(movie)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(compositeDisposable)
    }

    fun deleteMovieCart(movie: Movie) {
        Completable.fromAction {
            deleteMoviesCartUseCase.deleteCartMovie(movie.toCartMovie())
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(compositeDisposable)
    }
}
