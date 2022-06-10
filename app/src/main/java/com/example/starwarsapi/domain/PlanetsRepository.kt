package com.example.starwarsapi.domain

import com.example.starwarsapi.domain.PlanetsDomain

interface PlanetsRepository {
    suspend fun selectPlanetsByPage(page:Int): PlanetsDomain
}