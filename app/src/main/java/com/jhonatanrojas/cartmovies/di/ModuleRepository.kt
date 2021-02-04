package com.jhonatanrojas.cartmovies.di

import com.jhonatanrojas.cartmovies.data.api.MovieApi
import com.jhonatanrojas.cartmovies.data.repository.MovieRepositoryImpl
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleRepository {
    @Provides
    @Singleton
    fun providerMoviesRepository(movieApi: MovieApi): IMovieRepository {
        return MovieRepositoryImpl(movieApi)
    }
}