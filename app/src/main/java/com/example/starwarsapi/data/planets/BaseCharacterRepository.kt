package com.example.starwarsapi.data.planets

import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.domain.planets.CharacterDomain
import com.example.starwarsapi.domain.planets.CharacterRepository
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud.Mapper as charactersMapper
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud.Mapper.Factory as charactersMapperFactory

class BaseCharacterRepository(
    private val charactersCacheDataSource: CharactersCacheDataSource.Mutable,
    private val characterService: CharacterCloudDataSource,
    private val charactersCloudMapperFac: charactersMapperFactory<charactersMapper<CharactersCache>, Int>,
    private val charactersCacheToListMapper: CharactersCache.Mapper<List<CharacterCache>>,
    private val charactersCacheToListDomainMapper: CharactersCache.Mapper<List<CharacterDomain>>,
    private val charactersCacheToListUrlMapper: CharactersCache.Mapper<List<String>>
) : CharacterRepository {

    override suspend fun selectCharacters(
        planetId: Int
    ): List<CharacterDomain> {
        val result: List<CharacterDomain>
        val cache = charactersCacheDataSource.read(planetId)
        if (cache.isEmpty()) {
            result = listOf(CharacterDomain.Base(-1, "", -1, ""))
        }
        else
        if (cache.isFull()) {
            result = cache.map(charactersCacheToListDomainMapper)
        } else {
            val listOfUrl = cache.map(charactersCacheToListUrlMapper)
            val mapper = charactersCloudMapperFac.create(planetId)
            val charactersCloud = characterService.getCharactersById(listOfUrl)
            val newCache = charactersCloud.map(mapper)
            charactersCacheDataSource.save(newCache.map(charactersCacheToListMapper))
            result = newCache.map(charactersCacheToListDomainMapper)
        }
        return result


    }
}