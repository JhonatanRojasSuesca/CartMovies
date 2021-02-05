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
import com.jhonatanrojas.cartmovies.databinding.FragmentCartBinding
import com.jhonatanrojas.cartmovies.ui.viewmodel.cart.CartViewModel
import com.jhonatanrojas.cartmovies.ui.viewmodel.cart.CartViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CartFragment : Fragment() {

    @Inject
    lateinit var cartViewModel: CartViewModel

    @Inject
    lateinit var cartViewModelFactory: CartViewModelFactory

    private lateinit var bindingCartFragment: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingCartFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return bindingCartFragment.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        cartViewModel = ViewModelProvider(this, cartViewModelFactory).get(cartViewModel::class.java)
        bindingCartFragment.cartViewModel = cartViewModel
        cartViewModel.getCartMoviesFromDatabase()
        cartViewModel.cartMovies.observe(
            viewLifecycleOwner,
            Observer {
                if (it.isNotEmpty()) {
                    cartViewModel.setMoviesInRecyclerAdapter(it)
                    bindingCartFragment.messageEmpty.visibility = View.GONE
                } else {
                    bindingCartFragment.messageEmpty.visibility = View.VISIBLE
                }

            })
        cartViewModel.idMovie.observe(
            viewLifecycleOwner,
            {
                val bundle = bundleOf("id" to it)
                findNavController().navigate(R.id.action_CartFragment_to_DetailFragment, bundle)
            }
        )
    }
}