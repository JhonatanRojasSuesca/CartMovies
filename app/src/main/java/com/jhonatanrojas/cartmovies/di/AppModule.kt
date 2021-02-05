package com.jhonatanrojas.cartmovies.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jhonatanrojas.cartmovies.data.services.MoviesApi
import com.jhonatanrojas.cartmovies.ui.viewmodel.cart.CartViewModelFactory
import com.jhonatanrojas.cartmovies.ui.viewmodel.detail.DetailViewModelFactory
import com.jhonatanrojas.cartmovies.ui.viewmodel.home.HomeViewModelFactory
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
    fun provideMainViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory =
        factory

    @Provides
    @Singleton
    @JvmStatic
    fun provideDetailViewModelFactory(factory: DetailViewModelFactory): ViewModelProvider.Factory =
        factory
    @Provides
    @Singleton
    @JvmStatic
    fun provideCartViewModelFactory(factory: CartViewModelFactory): ViewModelProvider.Factory =
        factory

    @Provides
    @Singleton
    @JvmStatic
    fun provideMovieApiServices(retrofit: Retrofit): MoviesApi =
        retrofit.create(MoviesApi::class.java)


}