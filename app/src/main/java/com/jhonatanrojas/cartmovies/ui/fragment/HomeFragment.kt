package com.jhonatanrojas.cartmovies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.databinding.FragmentHomeBinding
import com.jhonatanrojas.cartmovies.ui.viewmodel.home.HomeViewModel
import com.jhonatanrojas.cartmovies.ui.viewmodel.home.HomeViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    private lateinit var bindingHomeFragment: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingHomeFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return bindingHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(homeViewModel::class.java)
        bindingHomeFragment.movieList = homeViewModel
        homeViewModel.getMoviesFromDatabase()
        homeViewModel.movies.observe(
            viewLifecycleOwner,
            Observer { homeViewModel.setMoviesInRecyclerAdapter(it) })
        homeViewModel.idMovie.observe(
            viewLifecycleOwner,
            {
                val bundle = bundleOf("id" to it)
                findNavController().navigate(R.id.action_Home_to_detail, bundle)
            }
        )
    }
}