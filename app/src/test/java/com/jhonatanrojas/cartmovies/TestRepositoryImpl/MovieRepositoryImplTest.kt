package com.jhonatanrojas.cartmovies.TestRepositoryImpl

import com.jhonatanrojas.cartmovies.data.api.MovieApi
import com.jhonatanrojas.cartmovies.data.local.MovieDao
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.data.repository.MovieRepositoryImpl
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {
    @Mock
    lateinit var movieApi: MovieApi

    @Mock
    lateinit var movieDao: MovieDao
    private lateinit var sut: MovieRepositoryImpl
    private lateinit var movieModel: Movie

    @Before
    fun setUp() {
        sut = MovieRepositoryImpl(movieApi, movieDao)
        movieModel = Movie(id = 1, poster_path = "sss.png", overview = "this is the Movie", backdrop_path = "aaa.png", original_title = "spiderman", isAddCart = true)
    }

    @Test
    fun `getListMovie`() {
        given(movieApi.getMovies(any())).willReturn(Observable.just(mock()))
        sut.getMovies(1).test()
        verify(movieApi).getMovies(any())
    }

    @Test
    fun `getListMovie returns error on failure`() {
        val error = Throwable()
        given(movieApi.getMovies(any())).willReturn(Observable.error(error))
        sut.getMovies(1).test().assertError(error)
    }

    @Test
    fun `getListMovie database`() {
        given(movieDao.getAll()).willReturn(Observable.just(mock()))
        sut.getMoviesDatabase().test()
        verify(movieDao).getAll()
    }

    @Test
    fun `getListMovie database returns error on failure`() {
        val error = Throwable()
        given(movieDao.getAll()).willReturn(Observable.error(error))
        sut.getMoviesDatabase().test().assertError(error)
    }

    @Test
    fun `getMoviebyId`() {
        given(movieDao.getMovieById(any())).willReturn(Observable.just(mock()))
        sut.getMovieDatabaseById(1).test()
        verify(movieDao).getMovieById(any())
    }

    @Test
    fun `updateMovie`() {
        sut.updateMovieAddCart(1, true)
        verify(movieDao).updateMovieAddCart(any(), any())
    }

    @Test
    fun `insertMovie`() {
        sut.insertMoviesDB(movieModel)
        verify(movieDao).insertMovies(any())
    }

    @Test
    fun `updateAllMovieDeleteCart`() {
        sut.updateAllMovieDeleteCart(true)
        verify(movieDao).updateAllMovieDeleteCart(any())
    }

    @Test
    fun `updateMovies`() {
        sut.updateMovieDB(movieModel)
        verify(movieDao).updateMovies(any())
    }
}