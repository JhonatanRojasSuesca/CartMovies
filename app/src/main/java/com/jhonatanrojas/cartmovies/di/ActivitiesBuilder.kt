package com.jhonatanrojas.cartmovies.di

import com.jhonatanrojas.cartmovies.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}