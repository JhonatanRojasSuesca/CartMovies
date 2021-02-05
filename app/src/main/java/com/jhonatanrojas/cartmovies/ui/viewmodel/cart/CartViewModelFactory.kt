package com.jhonatanrojas.cartmovies.ui.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CartViewModelFactory @Inject constructor(private val cartViewModel: CartViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(cartViewModel::class.java)) {
            return cartViewModel as T
        }
        throw IllegalArgumentException("uknown class name")
    }
}
