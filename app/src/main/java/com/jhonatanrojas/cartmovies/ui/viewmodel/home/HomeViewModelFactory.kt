package com.jhonatanrojas.cartmovies.ui.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val homeViewModel: HomeViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(homeViewModel::class.java)) {
            return homeViewModel as T
        }
        throw IllegalArgumentException("uknown class name")
    }
}
