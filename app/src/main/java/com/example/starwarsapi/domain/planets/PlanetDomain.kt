package com.example.starwarsapi.domain.planets

import com.example.starwarsapi.domain.planets.smartcontract.PlanetDomainToDomainWithResidence
import com.example.starwarsapi.domain.planets.smartcontract.PlanetDomainToItemUI
import com.example.starwarsapi.presentation.planets.items.CharacterItem
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator

interface PlanetDomain {
    suspend fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val id: Int,
        private val name: String,
        private val listOfResidence: List<String>
    ) : PlanetDomain {
        override suspend fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(id, name, listOfResidence)
        }
    }

    data class BaseWithResidence(
        private val id: Int,
        private val name: String,
        private val listOfResidence: List<CharacterDomain>
    ) : PlanetDomain {
        override suspend fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(id, name, listOfResidence)
        }
    }

    interface Mapper<T> {
        suspend fun map(id: Int, name: String, listOfResidence: List<String>): T
        fun map(id: Int, name: String, listOfResidence: List<CharacterDomain>): T

        class BaseToUI(charMapper: CharacterDomain.Mapper<CharacterItem>, private val listMutator: ListMutator) :
            PlanetDomainToItemUI(charMapper,listMutator)


        class BaseToPlanetWithResidence(baseCharacterRepository: CharacterRepository) :
            PlanetDomainToDomainWithResidence(baseCharacterRepository)
    }
}