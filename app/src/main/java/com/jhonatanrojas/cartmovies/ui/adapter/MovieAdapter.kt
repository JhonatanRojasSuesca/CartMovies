package com.jhonatanrojas.cartmovies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jhonatanrojas.cartmovies.BR
import com.jhonatanrojas.cartmovies.data.models.Movie
import com.jhonatanrojas.cartmovies.ui.viewmodel.home.HomeViewModel


class MovieAdapter internal constructor(var homeViewModel: HomeViewModel, var resource: Int) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies: List<Movie> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setMoviesCard(homeViewModel, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return resource
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovieList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setMoviesCard(homeViewModel: HomeViewModel, position: Int) {
            binding?.setVariable(BR.itemMovie, homeViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }

    }
}
