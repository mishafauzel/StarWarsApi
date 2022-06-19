package com.example.starwarsapi.presentation.character

import androidx.fragment.app.FragmentManager
import com.github.johnnysc.coremvvm.presentation.BaseFragment
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class MyShowStrategy {
    object REPLACE_WITH_ADD_TO_BACKSTACK : ShowStrategy() {

        override fun show(
            id: String,
            clasz: Class<out BaseFragment<*>>,
            containerId: Int,
            fragmentManager: FragmentManager
        ) {
            fragmentManager.beginTransaction()
                .replace(containerId, clasz.newInstance()).addToBackStack(id)
                .commit()
        }
    }
}