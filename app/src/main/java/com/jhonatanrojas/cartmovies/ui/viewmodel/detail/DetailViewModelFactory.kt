package com.jhonatanrojas.cartmovies.ui.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(private val detailViewModel: DetailViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(detailViewModel::class.java)) {
            return detailViewModel as T
        }
        throw IllegalArgumentException("uknown class name")
    }
}
