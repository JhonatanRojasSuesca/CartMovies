package com.jhonatanrojas.cartmovies.di

import com.jhonatanrojas.cartmovies.data.api.MovieApi
import com.jhonatanrojas.cartmovies.data.local.CartMovieDao
import com.jhonatanrojas.cartmovies.data.local.MovieDao
import com.jhonatanrojas.cartmovies.data.repository.CartMovieRepositoryImpl
import com.jhonatanrojas.cartmovies.data.repository.MovieRepositoryImpl
import com.jhonatanrojas.cartmovies.domain.repository.ICartMovieRepository
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleRepository {
    @Provides
    @Singleton
    fun providerMoviesRepository(movieApi: MovieApi, movieDao: MovieDao): IMovieRepository {
        return MovieRepositoryImpl(movieApi, movieDao)
    }

    @Provides
    @Singleton
    fun providerCartMoviesRepository(cartMovieDao: CartMovieDao): ICartMovieRepository {
        return CartMovieRepositoryImpl(cartMovieDao)
    }
}