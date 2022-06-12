package com.example.starwarsapi.presentation.main

import androidx.fragment.app.FragmentManager
import com.example.starwarsapi.presentation.character.CharacterFullInfoScreen
import com.example.starwarsapi.presentation.character.MyShowStrategyp
import com.example.starwarsapi.presentation.planets.PlanetNavigationScreen
import com.github.johnnysc.coremvvm.presentation.FragmentFactory
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class BaseFragmentFactory(
    containerId: Int,
    fragmentManager: FragmentManager,
) : FragmentFactory.Abstract(
    containerId,
    fragmentManager,
)  {
    override val screens: List<NavigationScreen>
        get() = listOf(PlanetNavigationScreen(),CharacterFullInfoScreen(MyShowStrategyp.REPLACE_WITH_ADD_TO_BACKSTACK))
}