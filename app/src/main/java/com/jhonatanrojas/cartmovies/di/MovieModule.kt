package com.jhonatanrojas.cartmovies.di


import com.jhonatanrojas.cartmovies.ui.fragment.CartFragment
import com.jhonatanrojas.cartmovies.ui.fragment.DetailFragment
import com.jhonatanrojas.cartmovies.ui.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieModule {

    @ContributesAndroidInjector
    abstract fun bindingDetailFragment(): DetailFragment
    @ContributesAndroidInjector
    abstract fun bindingHomeFragment(): HomeFragment
    @ContributesAndroidInjector
    abstract fun bindingCartFragment(): CartFragment
}