package com.jhonatanrojas.cartmovies.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesUseCase
import com.jhonatanrojas.cartmovies.ui.adapter.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase ) : ViewModel() {

    var movies: MutableLiveData<List<Movie>> = MutableLiveData()
    private var adapter : MovieAdapter? = null

    fun getMovies(){
        getMoviesUseCase.getMoviesFromApi(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{handlerMoviesResult(it) }
    }

    private fun handlerMoviesResult(result: Result) {
        when(result){
            is Result.Success ->{
                movies.postValue(result.data)
                Log.e("mainviewModel", "mensaje correcto ${result.data}")
            }
            is Result.Failure ->{
                Log.e("mainviewModel", "mensaje incorrecto ${result.throwable}")
            }
            Result.Loading -> {
                Log.e("mainviewModel", "mensaje Loading ")
            }
        }
    }

    fun getMovieAt(position:Int): Movie?{
        val movies: MutableLiveData<List<Movie>> = movies
        return movies.value?.get(position)
    }

    fun setMoviesInRecyclerAdapter(movies: List<Movie>) {
        adapter?.setMovieList(movies)
    }

    fun getRecyclerMovieAdapter(): MovieAdapter?{
        adapter = MovieAdapter(this, R.layout.item_movies)
        return  adapter
    }
}