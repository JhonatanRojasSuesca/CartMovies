package com.jhonatanrojas.cartmovies.TestUserCase

import com.jhonatanrojas.cartmovies.RxJavaTestHooksResetRule
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieUseCaseTest {
    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var iMovieRepository: IMovieRepository
    private lateinit var sut:  GetMoviesUseCase

    @Before
    fun setUp() {
        sut = GetMoviesUseCase(iMovieRepository)
    }

    @Test
    fun `Error Throwable byId`() {
        given(iMovieRepository.getMovieDatabaseById(any())).willReturn(Observable.error(Throwable()))

        sut.getMovieFromDatabaseById(1).test()

        verify(iMovieRepository).getMovieDatabaseById(any())
    }

    @Test
    fun `shows loading to start`() {
        given(iMovieRepository.getMovies(any())).willReturn(Observable.just(mock()))

        sut.getMoviesFromApi(1).test().assertValueAt(0){(it== Result.Loading)}

    }

    @Test
    fun `returns the success result when success retrieving the movie list`() {
        val cartMovieList = arrayListOf<Movie>()

        given(iMovieRepository.getMoviesDatabase()).willReturn(Observable.just(cartMovieList))

        sut.getMoviesFromDatabase().test()
           .assertValueAt(1) { ((it as Result.Success).data as List<Movie>) == cartMovieList }
    }

}