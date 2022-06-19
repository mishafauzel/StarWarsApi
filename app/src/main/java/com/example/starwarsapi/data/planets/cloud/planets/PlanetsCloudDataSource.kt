package com.example.starwarsapi.data.planets.cloud.planets

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError


interface PlanetsCloudDataSource {

    suspend fun planetsByPage(page: Int): PlanetsCloud

    class Base(private val planetService: PlanetService, handleError: HandleError) :
        CloudDataSource.Abstract(handleError), PlanetsCloudDataSource {

        override suspend fun planetsByPage(page: Int) = handle {
            return@handle planetService.getPlanetsByPage(page)
        }


    }
}