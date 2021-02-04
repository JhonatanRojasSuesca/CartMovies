package com.jhonatanrojas.cartmovies.data.models

import com.google.gson.annotations.SerializedName

data class MoviesResult(
    @SerializedName("id") val id: Int,
    @SerializedName("items")val items: List<Movie>,
)


data class Movie(
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @SerializedName("id") val id : Int,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
)