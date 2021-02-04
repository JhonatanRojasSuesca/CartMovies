package com.jhonatanrojas.cartmovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val homeViewModel: HomeViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(homeViewModel::class.java)) {
            return homeViewModel as T
        }
        throw IllegalArgumentException("uknown class name")
    }
}
