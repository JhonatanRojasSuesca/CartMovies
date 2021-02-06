package com.jhonatanrojas.cartmovies.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jhonatanrojas.cartmovies.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        val uri : Uri? = intent.data
        uri?.path?.let {
            val path = it.substring(1)
            val isNumeric = path.matches("-?\\d+(\\.\\d+)?".toRegex())
            if(isNumeric){
                val bundle = bundleOf("id" to path.toInt())
                navController.navigate(R.id.action_Home_to_detail, bundle)
            }else{
                Toast.makeText(this@MainActivity," DeepLink Fail ${uri.toString()}",Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}