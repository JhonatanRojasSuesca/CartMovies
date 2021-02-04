package com.jhonatanrojas.cartmovies.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MoviesResult(
    @SerializedName("id") val id: Int,
    @SerializedName("items")val items: List<Movie>,
)

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") val id : Int,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title") val original_title: String,
    @ColumnInfo(name = "overview")
    @SerializedName("overview") val overview: String,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") val poster_path: String,
)