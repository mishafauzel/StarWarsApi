package com.example.starwarsapi.domain.planets

interface PlanetsRepository {
    suspend fun selectPlanetsByPage(page: Int): PlanetsDomain
}