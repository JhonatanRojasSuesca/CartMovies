package com.jhonatanrojas.cartmovies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.databinding.FragmentHomeBinding
import com.jhonatanrojas.cartmovies.ui.viewmodel.MainViewModel
import com.jhonatanrojas.cartmovies.ui.viewmodel.MainViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var bindingHomeFragment : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        bindingHomeFragment =  DataBindingUtil.inflate(inflater,R.layout.fragment_home, container,false)
        return bindingHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(mainViewModel::class.java)
        bindingHomeFragment.movieList = mainViewModel
        mainViewModel.getMovies()
        mainViewModel.movies.observe(
            viewLifecycleOwner,
            Observer { mainViewModel.setMoviesInRecyclerAdapter(it) })
    }
}