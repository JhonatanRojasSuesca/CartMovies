package com.jhonatanrojas.cartmovies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.databinding.FragmentDetailBinding
import com.jhonatanrojas.cartmovies.ui.viewmodel.detail.DetailViewModel
import com.jhonatanrojas.cartmovies.ui.viewmodel.detail.DetailViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DetailFragment : Fragment() {
    @Inject
    lateinit var detailViewModel: DetailViewModel

    @Inject
    lateinit var detailViewModelFactory: DetailViewModelFactory

    private lateinit var bindingDetailFragment: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetailFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return bindingDetailFragment.root
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().navigate(getActionNavigation(findNavController().currentDestination!!.id))
        }
    }

    private fun getActionNavigation(id: Int): Int {
        return when (id) {
            R.id.HomeFragment -> {
                R.id.action_DetailFragment_to_HomeFragment
            }
            R.id.CartFragment -> {
                R.id.action_DetailFragment_to_CartFragment
            }
            else -> {
                R.id.action_DetailFragment_to_HomeFragment
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        detailViewModel =
            ViewModelProvider(this, detailViewModelFactory).get(detailViewModel::class.java)
        var id = arguments?.getInt("id")
        id?.let { detailViewModel.getMovieById(it) }
        bindingDetailFragment.detailMovie = detailViewModel
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
        detailViewModel.onErrorId.observe(viewLifecycleOwner, {
            Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_DetailFragment_to_HomeFragment)
        })
    }
}
