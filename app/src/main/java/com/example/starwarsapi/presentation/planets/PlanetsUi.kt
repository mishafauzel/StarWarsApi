package com.example.starwarsapi.presentation.planets

interface PlanetsUi {
    class Base(private val pagerUI: PagerUI, private val planetUI: List<PlanetUI>): PlanetsUi
}