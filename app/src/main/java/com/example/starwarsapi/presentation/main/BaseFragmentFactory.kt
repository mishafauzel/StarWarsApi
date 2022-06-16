package com.example.starwarsapi.presentation.main

import androidx.fragment.app.FragmentManager
import com.example.starwarsapi.presentation.character.nav_screen.CharacterFullInfoScreen
import com.example.starwarsapi.presentation.character.MyShowStrategy
import com.example.starwarsapi.presentation.planets.nav_screen.PlanetNavigationScreen
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.presentation.NavigationScreen

class BaseFragmentFactory(
    containerId: Int,
    fragmentManager: FragmentManager,
) : FragmentFactory.Abstract(
    containerId,
    fragmentManager,
)  {
    override val screens: List<NavigationScreen>
        get() = listOf(PlanetNavigationScreen(),
            CharacterFullInfoScreen(MyShowStrategy.REPLACE_WITH_ADD_TO_BACKSTACK)
        )
}