package com.jhonatanrojas.cartmovies.ui.viewmodel.cart

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.core.utils.addTo
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CartViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase ) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    var movie = ObservableField<Movie>()
    fun getMovieById(id : Int){
        getMoviesUseCase.getMovieFromDatabaseById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{handlerMoviesDatabaseResult(it)}.addTo(compositeDisposable)
    }

    private fun handlerMoviesDatabaseResult(result: Result) {
        when(result){
            is Result.Success ->{
                movie.set(result.data as Movie)
                Log.e("mainviewModel", "mensaje Detalle ${(result.data) as Movie}")
            }
            is Result.Failure ->{
                Log.e("mainviewModel", "mensaje incorrecto ${result.throwable}")
            }
            Result.Loading -> {
                Log.e("mainviewModel", "mensaje Loading ")
            }
        }
    }
}
