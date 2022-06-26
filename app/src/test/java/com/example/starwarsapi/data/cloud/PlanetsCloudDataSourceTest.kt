package com.example.starwarsapi.data.cloud

import com.example.starwarsapi.data.planets.cloud.planets.PlanetCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloud
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import java.net.UnknownHostException

class PlanetsCloudDataSourceTest(private val isAvailable: Boolean, handleError: HandleError) :
    PlanetsCloudDataSource,
    CloudDataSource.Abstract(handleError) {
    private val mutableMapOfPlanets: MutableMap<Int, PlanetsCloud> = mutableMapOf()

    init {
        if (isAvailable) {
            val firstPagePlanetsList = listOf(
                PlanetCloud.Base(
                    "1",
                    "https://swapi.dev/api/planets/1/", listOf(
                        "https://swapi.dev/api/people/1/",
                        "https://swapi.dev/api/people/2/"
                    )
                ),
                PlanetCloud.Base(
                    "2",
                    "https://swapi.dev/api/planets/2/", listOf(
                        "https://swapi.dev/api/people/3/",
                        "https://swapi.dev/api/people/4/"
                    )
                )
            )
            val secondPagePlanetsList = listOf(
                PlanetCloud.Base(
                    "3",
                    "https://swapi.dev/api/planets/3/", listOf(
                        "https://swapi.dev/api/people/5/",
                        "https://swapi.dev/api/people/6/"
                    )
                ),
                PlanetCloud.Base(
                    "4",
                    "https://swapi.dev/api/planets/4/", listOf(
                        "https://swapi.dev/api/people/7/",
                        "https://swapi.dev/api/people/8/"
                    )
                )
            )
            val firstPage = PlanetsCloud.Base(
                "https://swapi.dev/api/planets/?page=2",
                firstPagePlanetsList
            )
            val secondPage = PlanetsCloud.Base(
                "", secondPagePlanetsList
            )
            mutableMapOfPlanets[1] = firstPage
            mutableMapOfPlanets[2] = secondPage
        }
    }

    override suspend fun planetsByPage(page: Int) = handle {
        if (isAvailable) {
            val result=mutableMapOfPlanets[page]
            println(result)
            return@handle result!!
        } else throw UnknownHostException()
    }
}