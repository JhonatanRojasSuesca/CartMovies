package com.jhonatanrojas.cartmovies.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cartMovie")
class CartMovie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
)