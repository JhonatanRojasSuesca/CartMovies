package com.jhonatanrojas.cartmovies.core.utils

import com.bumptech.glide.Glide
import com.jhonatanrojas.cartmovies.ui.component.AspectRatioImageView


fun AspectRatioImageView.loadMovieImage(path: String) {
    Glide
        .with(this.context)
        .load("https://image.tmdb.org/t/p/w500/${path}")
        .into(this)
}