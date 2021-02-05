package com.jhonatanrojas.cartmovies.core

sealed class Result {
    object Loading : Result()
    data class Success(val data: Any) : Result()
    data class Failure(val throwable: Throwable) : Result()
}