package com.jhonatanrojas.cartmovies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val mainViewModel: MainViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(mainViewModel::class.java)) {
            return mainViewModel as T
        }
        throw IllegalArgumentException("uknown class name")
    }
}
