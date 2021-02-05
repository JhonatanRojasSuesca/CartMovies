package com.jhonatanrojas.cartmovies.core

import com.jhonatanrojas.cartmovies.data.models.Movie

sealed class Result {
    object Loading : Result()
    data class Success(val data: Any) : Result()
    data class Failure(val throwable: Throwable) : Result()
}