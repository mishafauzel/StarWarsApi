package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.core.Dispatchers


interface PlanetsInteractor {

    suspend fun getListOfPlanetsByPage(
        page: PagerData,
        successful: (PlanetsUi) -> Unit,
        atFinish: () -> Unit
    )

    class Base(
        private val mapper: PlanetsDomain.Mapper<PlanetsUi>,
        private val repository: PlanetsRepository,
        private val mapperDomainToDomainWithResidence: PlanetsDomain.Mapper<PlanetsDomain>,
        private val mapperPagerDataToInt: PagerData.Mapper<Int>,
        private val mapperDomainExceptionMapperToListOfItemsUIMapper: DomainException.Mapper.Factory<PlanetsUi>,
        private val dispatchers: Dispatchers,

        ) : PlanetsInteractor {

        override suspend fun getListOfPlanetsByPage(
            pageData: PagerData,
            successful: (PlanetsUi) -> Unit,
            atFinish: () -> Unit
        ) {
            try {
                val result = repository.selectPlanetsByPage(pageData.map(mapperPagerDataToInt))
                val resultWithResidence = result.map(mapperDomainToDomainWithResidence)
                dispatchers.changeToUI { successful.invoke(resultWithResidence.map(mapper)) }
            } catch (domainEx: DomainException.AbstractDomainException) {
                dispatchers.changeToUI {
                    successful.invoke(
                        domainEx.map(
                            mapperDomainExceptionMapperToListOfItemsUIMapper.create(pageData)
                        )
                    )
                }
            } finally {
                dispatchers.changeToUI { atFinish.invoke() }
            }


        }
    }
}