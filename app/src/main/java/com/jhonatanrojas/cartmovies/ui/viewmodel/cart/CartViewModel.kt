package com.jhonatanrojas.cartmovies.ui.viewmodel.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.core.utils.addTo
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.useCase.DeleteMoviesCartUseCase
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesCartUseCase
import com.jhonatanrojas.cartmovies.ui.adapter.CartMovieAdapter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CartViewModel @Inject constructor(private val getCartMoviesUseCase: GetMoviesCartUseCase, private val deleteMoviesCartUseCase: DeleteMoviesCartUseCase) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    var cartMovies: MutableLiveData<List<CartMovie>> = MutableLiveData()
    var idMovie: MutableLiveData<Int> = MutableLiveData()
    private var adapter: CartMovieAdapter? = null
    fun getCartMoviesFromDatabase() {
        getCartMoviesUseCase.getCartMoviesFromDatabase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handlerMoviesDatabaseResult(it) }.addTo(compositeDisposable)
    }

    private fun handlerMoviesDatabaseResult(result: Result) {
        when (result) {
            is Result.Success -> {
                cartMovies.postValue(result.data as List<CartMovie>)
                Log.e("mainviewModel", "mensaje Detalle ${(result.data) as List<CartMovie>}")
            }
            is Result.Failure -> {
                Log.e("mainviewModel", "mensaje incorrecto ${result.throwable}")
            }
            Result.Loading -> {
                Log.e("mainviewModel", "mensaje Loading ")
            }
        }
    }

    fun deleteMovieCart(position: Int) {
        Completable.fromAction {
            getMovieAt(position)?.let {
                deleteMoviesCartUseCase.deleteCartMovie(it)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(compositeDisposable)
    }

    fun getMovieAt(position: Int): CartMovie? {
        val movies: MutableLiveData<List<CartMovie>> = cartMovies
        return movies.value?.get(position)
    }

    fun goToDetail(position: Int) {
        idMovie.postValue(getMovieAt(position)?.id)
    }

    fun setMoviesInRecyclerAdapter(movies: List<CartMovie>) {
        adapter?.setMovieList(movies)
    }

    fun getRecyclerMovieAdapter(): CartMovieAdapter? {
        adapter = CartMovieAdapter(this, R.layout.item_cart_movies)
        return adapter
    }
}
