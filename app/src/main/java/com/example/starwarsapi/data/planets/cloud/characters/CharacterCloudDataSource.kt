package com.example.starwarsapi.data.planets.cloud.characters

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface CharacterCloudDataSource {

    suspend fun getCharactersById(listOfIds: List<String>): CharactersCloud

    class Base(private val characterService: CharacterService, handleError: HandleError) :
        CloudDataSource.Abstract(handleError), CharacterCloudDataSource {

        override suspend fun getCharactersById(listOfIds: List<String>) = handle {
            CharactersCloud.Base(listOfIds.map { id ->
                characterService.getCharacterById(id)
            })
        }
        }
}