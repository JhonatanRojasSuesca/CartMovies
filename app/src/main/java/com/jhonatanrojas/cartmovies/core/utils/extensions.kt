package com.jhonatanrojas.cartmovies.core.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jhonatanrojas.cartmovies.data.models.CartMovie
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.ui.component.AspectRatioImageView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

const val URL_BASE_IMG = "https://image.tmdb.org/t/p/w500/"
fun AspectRatioImageView.loadMovieImage(path: String) {
    Glide
        .with(this.context)
        .load(URL_BASE_IMG + path)
        .into(this)
}

fun ImageView.loadMovieImage(path: String) {
    Glide
        .with(this.context)
        .load(URL_BASE_IMG + path)
        .into(this)
}

@JvmName("addToComposite")
fun Disposable.addTo(disposableComposite: CompositeDisposable) {
    disposableComposite.add(this)
}

fun Movie.toCartMovie() = CartMovie(
    id,
    poster_path,
    original_title
)

fun TextView.setVisibility(isVisibility: Boolean) {
    this.visibility = if (isVisibility) View.VISIBLE else View.GONE
}