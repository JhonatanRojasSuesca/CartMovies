package com.jhonatanrojas.cartmovies.TestUserCase

import com.jhonatanrojas.cartmovies.RxJavaTestHooksResetRule
import com.jhonatanrojas.cartmovies.core.Result
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.useCase.GetMoviesCartUseCase

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
class GetMovieCartUseCaseTest {
    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var iCartMovieRepository : ICartMovieRepository
    private lateinit var sut:  GetMoviesCartUseCase

    @Before
    fun setUp() {
        sut = GetMoviesCartUseCase(iCartMovieRepository)
    }

    @Test
    fun `Error observable List`() {
        given(iCartMovieRepository.getAllCart()).willReturn(Observable.error(Throwable()))

        sut.getCartMoviesFromDatabase().test()

        verify(iCartMovieRepository).getAllCart()
    }

    @Test
    fun `shows loading to start`() {
        given(iCartMovieRepository.getAllCart()).willReturn(Observable.just(mock()))

        sut.getCartMoviesFromDatabase().test().assertValueAt(0){(it== Result.Loading)}

    }

    @Test
    fun `returns the success result when success retrieving the movie list`() {
        val cartMovieList = arrayListOf<CartMovie>()
        given(iCartMovieRepository.getAllCart()).willReturn(Observable.just(cartMovieList))

        sut.getCartMoviesFromDatabase().test()
            .assertValueAt(1) { ((it as Result.Success).data as List<CartMovie>) == cartMovieList }
    }

    @Test
    fun `returns the failure result when error retrieving the restaurant list`() {
        val throwable = Throwable()
        given(iCartMovieRepository.getAllCart()).willReturn(Observable.error(throwable))

        sut.getCartMoviesFromDatabase().test()
            .assertValueAt(1) { (it as Result.Failure).throwable == throwable }
    }
}