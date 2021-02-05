package com.jhonatanrojas.cartmovies.core.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

import com.jhonatanrojas.cartmovies.ui.component.AspectRatioImageView

@BindingAdapter("loadimage")
fun bindingImage(imageView: ImageView, path: String?) {
    if (path != null) {
        imageView.loadMovieImage(path)
    }
}

@BindingAdapter("imageUrl")
fun getImageBinding(imgMovie: AspectRatioImageView, imageUrl: String) {
    imgMovie.loadMovieImage(imageUrl)
}

@BindingAdapter("visibilityDelete")
fun setVisibilityBinding(textView: TextView, visible: Boolean) {
    textView.setVisibility(visible)
}