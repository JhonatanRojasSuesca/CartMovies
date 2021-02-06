package com.jhonatanrojas.cartmovies.TestUserCase

import com.jhonatanrojas.cartmovies.RxJavaTestHooksResetRule
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import com.jhonatanrojas.cartmovies.domain.useCase.InsertMoviesCartUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InsertMovieCartUseCaseTest {
    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var iCartMovieRepository : ICartMovieRepository
    @Mock
    lateinit var iMovieRepository: IMovieRepository
    private lateinit var sut:  InsertMoviesCartUseCase
    private lateinit var movieModel: Movie

    @Before
    fun setUp() {
        sut = InsertMoviesCartUseCase(iCartMovieRepository,iMovieRepository)
        movieModel = Movie(id = 1, poster_path = "sss.png", overview = "this is the Movie", backdrop_path = "aaa.png", original_title = "spiderman", isAddCart = true)
    }

    @Test
    fun `insertCart `() {

        sut.insertCartMovie(movieModel)

        verify(iCartMovieRepository).insertMoviesCartDB(any())
        verify(iMovieRepository).updateMovieAddCart(any(), any())
    }

}