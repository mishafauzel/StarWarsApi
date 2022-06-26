package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor


interface PlanetsInteractor {

    suspend fun getListOfPlanetsByPage(
        successful: (PlanetsUi) -> Unit,
        atFinish: () -> Unit
    )

    class Base(
        private val mapper: PlanetsDomain.Mapper<PlanetsUi>,
        private val repository: PlanetsRepository,
        private val mapperDomainToDomainWithResidence: PlanetsDomain.Mapper<PlanetsDomain>,
        private val pagerDomainToInt:PagerDomain.Mapper<Int>,
        private val mapperPlanetsDomainToPagerDomain: PlanetsDomain.Mapper<PagerDomain>,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : PlanetsInteractor,Interactor.Abstract(dispatchers,handleError) {

        private var pagerInfo:PagerDomain=PagerDomain.Base(0,1)

        override suspend fun getListOfPlanetsByPage(
            successful: (PlanetsUi) -> Unit,
            atFinish: () -> Unit
        )= handle(successful = successful,atFinish)
        {
            val result=repository.selectPlanetsByPage(pagerInfo.map(pagerDomainToInt))
            val resultWithResidence=result.map(mapperDomainToDomainWithResidence)
            pagerInfo=resultWithResidence.map(mapperPlanetsDomainToPagerDomain)
            return@handle resultWithResidence.map(mapper)
        }
        }
}