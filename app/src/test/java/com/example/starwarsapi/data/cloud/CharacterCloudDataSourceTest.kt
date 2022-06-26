package com.example.starwarsapi.data.cloud

import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud
import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import java.net.UnknownHostException

class CharacterCloudDataSourceTest(private val isAvailable: Boolean, handleError: HandleError) :
    CharacterCloudDataSource, CloudDataSource.Abstract(handleError) {
    private val listIfCharacters = mutableMapOf<String, CharacterCloud>()

    init {
        if (isAvailable)
            for (i in 0..10) {
                val character = CharacterCloud.Base(
                    i.toString(),
                    i.toString(),
                    "https://swapi.dev/api/people/$i/"
                )
                listIfCharacters[i.toString()] = character

            }

    }

    override suspend fun getCharactersById(listOfIds: List<String>) = handle {
        println(listOfIds)
        if (isAvailable) {
            val result = mutableListOf<CharacterCloud>()
            println(listOfIds)
            for (id in listOfIds) {
                listIfCharacters[id]?.let {
                    result.add(it)
                }
            }
            return@handle CharactersCloud.Base(result)
        } else throw UnknownHostException()
    }
}