package com.jhonatanrojas.cartmovies.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jhonatanrojas.cartmovies.data.api.MovieApi
import com.jhonatanrojas.cartmovies.data.repository.MovieRepositoryImpl
import com.jhonatanrojas.cartmovies.data.services.MoviesApi
import com.jhonatanrojas.cartmovies.domain.repository.IMovieRepository
import com.jhonatanrojas.cartmovies.ui.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(moveApplication: MoveApplication): Context =
        moveApplication.applicationContext

    @Singleton
    @Provides
    @JvmStatic
    fun provideApplication(moveApplication: MoveApplication): Application = moveApplication

    @Provides
    @Singleton
    @JvmStatic
    fun provideMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory =
        factory

    @Provides
    @Singleton
    @JvmStatic
    fun provideMovieApiServices(retrofit: Retrofit): MoviesApi =
        retrofit.create(MoviesApi::class.java)


}