package com.jhonatanrojas.cartmovies.ui.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.core.utils.SingleLiveData
import com.jhonatanrojas.cartmovies.core.utils.addTo
import com.jhonatanrojas.cartmovies.core.utils.toCartMovie
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.useCase.DeleteMoviesCartUseCase
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesUseCase
import com.jhonatanrojas.cartmovies.domain.useCase.InsertMoviesCartUseCase
import com.jhonatanrojas.cartmovies.ui.adapter.MovieAdapter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase, private val insertMoviesCartUseCase: InsertMoviesCartUseCase, private val deleteMoviesCartUseCase: DeleteMoviesCartUseCase) : ViewModel() {


    var movies: SingleLiveData<List<Movie>> = SingleLiveData()
    var idMovie: MutableLiveData<Int> = MutableLiveData()
    private var adapter: MovieAdapter? = null
    private val compositeDisposable = CompositeDisposable()


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
                if ((result.data as List<Movie>).isEmpty()) getMovies(1)
            }
            is Result.Failure -> {
            }
            Result.Loading -> {
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

    fun insertMovieCart(position: Int) {
        Completable.fromAction {
            getMovieAt(position)?.let {
                insertMoviesCartUseCase.insertCartMovie(it)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(compositeDisposable)
    }

    fun deleteMovieCart(position: Int) {
        Completable.fromAction {
            getMovieAt(position)?.let {
                deleteMoviesCartUseCase.deleteCartMovie(it.toCartMovie())
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().addTo(compositeDisposable)
    }
}