package com.jhonatanrojas.cartmovies.TestRepositoryImpl

import com.jhonatanrojas.cartmovies.core.utils.toCartMovie
import com.jhonatanrojas.cartmovies.data.local.CartMovieDao
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.data.repository.CartMovieRepositoryImpl
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
class CartMovieRepositoryImplTest {
    @Mock
    lateinit var cartMovieApi: CartMovieDao

    private lateinit var sut: CartMovieRepositoryImpl
    private lateinit var movieModel: Movie

    @Before
    fun setUp() {
        sut = CartMovieRepositoryImpl(cartMovieApi)
        movieModel = Movie(id = 1, poster_path = "sss.png", overview = "this is the Movie", backdrop_path = "aaa.png", original_title = "spiderman", isAddCart = true)
    }


    @Test
    fun `insertMovieCart`() {
        sut.insertMoviesCartDB(movieModel.toCartMovie())
        verify(cartMovieApi).insertMovies(any())
    }

    @Test
    fun `deleteCartMovie`() {
        sut.deleteCartMovie(movieModel.toCartMovie())
        verify(cartMovieApi).deleteCartMovie(any())
    }

    @Test
    fun `deleteAllCartMovie`() {
        sut.deleteAllCartMovie()
        verify(cartMovieApi).deleteAllCartMovie()
    }

    @Test
    fun `getAllCart`() {
        given(cartMovieApi.getAll()).willReturn(Observable.just(mock()))
        sut.getAllCart()
        verify(cartMovieApi).getAll()
    }

}