package com.jhonatanrojas.cartmovies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        detailViewModel =
            ViewModelProvider(this, detailViewModelFactory).get(detailViewModel::class.java)
        var id = arguments?.getInt("id")
        id?.let { detailViewModel.getMovieById(it) }
        bindingDetailFragment.detailMovie = detailViewModel
    }
}
