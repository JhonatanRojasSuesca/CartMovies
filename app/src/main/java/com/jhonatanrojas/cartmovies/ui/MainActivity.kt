package com.jhonatanrojas.cartmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.jhonatanrojas.cartmovies.R
import com.jhonatanrojas.cartmovies.ui.viewmodel.HomeViewModel
import com.jhonatanrojas.cartmovies.ui.viewmodel.MainViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        Navigation.findNavController(this,R.id.nav_host_fragment)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}