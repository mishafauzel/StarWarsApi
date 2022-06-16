package com.example.starwarsapi.presentation.planets.nav_screen

import com.example.starwarsapi.presentation.planets.PlanetsFragment
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class PlanetNavigationScreen(showStrategy: ShowStrategy = ShowStrategy.REPLACE) :
    NavigationScreen(
        "PlanetsFragment",
        PlanetsFragment::class.java
        ,showStrategy
    )