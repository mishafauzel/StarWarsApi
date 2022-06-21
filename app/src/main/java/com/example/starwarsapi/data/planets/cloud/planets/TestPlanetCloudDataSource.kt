package com.example.starwarsapi.data.planets.cloud.planets

import java.net.UnknownHostException

class TestPlanetCloudDataSource(
    private val dataSource: MutableMap<Int, Pair<String, List<PlanetCloud.Base>>> = mutableMapOf()
) :
    PlanetsCloudDataSource {
    override suspend fun planetsByPage(page: Int): PlanetsCloud {
        val result = dataSource[page]
        if (result != null) {
            return PlanetsCloud.Base(result.first, result.second)
        } else
            throw  UnknownHostException()
    }

    sealed class State {
        class Empty : State()
        class HasSomeInfo : State()
    }
}