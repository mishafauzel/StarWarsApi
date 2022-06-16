package com.example.starwarsapi.domain.planets

import android.util.Log
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.presentation.planets.items.PagerItem
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor


interface PlanetsInteractor {
    suspend fun getListOfPlanetsByPage(
        pageNumber: Int, atFinish: () -> Unit,
        successful: (Pair<PlanetsUi,PagerData>) -> Unit
    )

    class Base(
        private val mapper: PlanetsDomain.Mapper<PlanetsUi>,
        private val repository: PlanetsRepository,
        private val mapperDomainToDomainWithResidence: PlanetsDomain.Mapper<PlanetsDomain>,
        private val mapperDomainToPagerItem:PlanetsDomain.Mapper<PagerData>,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : PlanetsInteractor, Interactor.Abstract(dispatchers, handleError) {
        override suspend fun getListOfPlanetsByPage(
            pageNumber: Int,
            atFinish: () -> Unit,
            successful:(Pair<PlanetsUi,PagerData>) -> Unit
        ) = handle(successful, atFinish) {
            val data = repository.selectPlanetsByPage(pageNumber)
            val dataWithResidence=data.map(mapperDomainToDomainWithResidence)

            return@handle Pair(dataWithResidence.map(mapper),dataWithResidence.map(mapperDomainToPagerItem))

        }
    }
}