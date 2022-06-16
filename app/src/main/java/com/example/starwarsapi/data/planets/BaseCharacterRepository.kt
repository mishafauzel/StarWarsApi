package com.example.starwarsapi.data.planets

import android.util.Log
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cloud.characters.CharacterService
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud
import com.example.starwarsapi.domain.planets.CharacterDomain
import com.example.starwarsapi.domain.planets.CharacterRepository
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud.Mapper.Factory as charactersMapperFactory
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud.Mapper as charactersMapper

class BaseCharacterRepository(
    private val charactersCacheDataSource: CharactersCacheDataSource.Mutable,
    private val characterService: CharacterService,
    private val charactersCloudMapperFac: charactersMapperFactory<charactersMapper<CharactersCache>, Int>,
    private val charactersCacheToListMapper: CharactersCache.Mapper<List<CharacterCache>>,
    private val charactersCacheToListDomainMapper: CharactersCache.Mapper<List<CharacterDomain>>,
    private val charactersCacheToListUrlMapper: CharactersCache.Mapper<List<String>>

) : CharacterRepository {
    override suspend fun selectCharacters(
        planetId: Int
    ): List<CharacterDomain> {

        val cache = charactersCacheDataSource.read(planetId)
        if (cache.isEmpty()) {

            return listOf(CharacterDomain.Base(-1, "", -1, ""))
        }
        if (!cache.isFull()) {

            val listOfUrl = cache.map(charactersCacheToListUrlMapper)
            val mapper = charactersCloudMapperFac.create(planetId)


            val charactersCloud = CharactersCloud.Base(listOfUrl.map {
                characterService.getCharacterById(it)
            })
            val newCache =
                charactersCloud.map(mapper)
            charactersCacheDataSource.save(newCache.map(charactersCacheToListMapper))
            return newCache.map(charactersCacheToListDomainMapper)

        } else {
            return cache.map(charactersCacheToListDomainMapper)
        }


    }
}