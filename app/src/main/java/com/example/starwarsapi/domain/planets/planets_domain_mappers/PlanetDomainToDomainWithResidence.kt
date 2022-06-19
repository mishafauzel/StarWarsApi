package com.example.starwarsapi.domain.planets.planets_domain_mappers

import com.example.starwarsapi.domain.planets.CharacterDomain
import com.example.starwarsapi.domain.planets.CharacterRepository
import com.example.starwarsapi.domain.planets.PlanetDomain

open class PlanetDomainToDomainWithResidence(private val baseCharacterRepository: CharacterRepository) :
    PlanetDomain.Mapper<PlanetDomain> {

    override suspend fun map(
        id: Int,
        name: String,
        listOfResidence: List<String>
    ): PlanetDomain.BaseWithResidence {
        val newList = baseCharacterRepository.selectCharacters(id)
        return PlanetDomain.BaseWithResidence(id, name, newList)
    }

    override fun map(
        id: Int,
        name: String,
        listOfResidence: List<CharacterDomain>
    ): PlanetDomain.BaseWithResidence {
        throw IllegalStateException()
    }


}
