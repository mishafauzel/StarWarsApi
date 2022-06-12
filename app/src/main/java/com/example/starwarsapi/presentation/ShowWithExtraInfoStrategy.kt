package com.example.starwarsapi.presentation

import androidx.fragment.app.FragmentManager
import com.example.starwarsapi.presentation.character.CharacterFullFragment
import com.github.johnnysc.coremvvm.presentation.BaseFragment
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class ShowWithExtraInfoStrategy(private val extraInfo: Int) : ShowStrategy() {
    override fun show(
        id: String,
        clasz: Class<out BaseFragment<*>>,
        containerId: Int,
        fragmentManager: FragmentManager
    ) {
        val newInstance =
            (clasz as Class<CharacterFullFragment>).getDeclaredConstructor(Int::class.java)
                .newInstance(extraInfo)
        fragmentManager.beginTransaction().replace(containerId, newInstance).addToBackStack(id)
            .commit()


    }
}