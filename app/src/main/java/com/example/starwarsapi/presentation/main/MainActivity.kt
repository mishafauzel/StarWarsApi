package com.example.starwarsapi.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelStoreOwner
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.sl.ProvideViewModel

class MainActivity : BackPress.Activity<MainViewModel>(), ProvideViewModel {
    private lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentFactory = BaseFragmentFactory(R.id.container, supportFragmentManager)
        viewModel = provideViewModel(MainViewModel::class.java, this)
        viewModel.observeNavigation(this) {
            fragmentFactory.fragment(navigationScreen = it)
        }
        viewModel.observeGlobalNavigationCommunication(this) {
            viewModel.navigate(it)
        }
        val progress = findViewById<View>(R.id.progressLayout)
        viewModel.observeProgress(this) {
            it.apply(progress)
        }
        viewModel.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }


    }

    override fun <T : androidx.lifecycle.ViewModel> provideViewModel(
        clazz: Class<T>,
        owner: ViewModelStoreOwner
    ): T = (application as ProvideViewModel).provideViewModel(clazz, owner)

}