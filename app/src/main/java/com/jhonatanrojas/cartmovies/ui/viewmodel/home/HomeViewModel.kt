package com.jhonatanrojas.cartmovies.ui.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.core.utils.addTo
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesUseCase
import com.jhonatanrojas.cartmovies.ui.adapter.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    var movies: MutableLiveData<List<Movie>> = MutableLiveData()
    var idMovie: MutableLiveData<Int> = MutableLiveData()
    private var adapter: MovieAdapter? = null
    private val compositeDisposable = CompositeDisposable()

    init {
        getMovies(1)
    }

    private fun getMovies(page: Int) {
        getMoviesUseCase.getMoviesFromApi(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(compositeDisposable)
    }

    fun getMoviesFromDatabase() {
        getMoviesUseCase.getMoviesFromDatabase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handlerMoviesDatabaseResult(it) }.addTo(compositeDisposable)
    }

    private fun handlerMoviesDatabaseResult(result: Result) {
        when (result) {
            is Result.Success -> {
                movies.postValue((result.data) as List<Movie>)
                Log.e("mainviewModel", "mensaje correcto ${result.data}")
            }
            is Result.Failure -> {
                Log.e("mainviewModel", "mensaje incorrecto ${result.throwable}")
            }
            Result.Loading -> {
                Log.e("mainviewModel", "mensaje Loading ")
            }
        }
    }

    fun getMovieAt(position: Int): Movie? {
        val movies: MutableLiveData<List<Movie>> = movies
        return movies.value?.get(position)
    }

    fun setMoviesInRecyclerAdapter(movies: List<Movie>) {
        adapter?.setMovieList(movies)
    }

    fun getRecyclerMovieAdapter(): MovieAdapter? {
        adapter = MovieAdapter(this, R.layout.item_movies)
        return adapter
    }

    fun goToDetail(position: Int) {
        idMovie.postValue(getMovieAt(position)?.id)
    }
}