package com.example.starwarsapi.presentation.character

import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import java.net.UnknownHostException

class CharacterCloudDataSourceTest(handleError: HandleError, isAvailable: Boolean) :
    CharacterFullInfoCloudDataSource, CloudDataSource.Abstract(handleError) {
    private val mutableMapOfCharacterCloud = mutableMapOf<String, CharacterFullInfoCloud.Base>()

    init {
        if (isAvailable) {
            mutableMapOfCharacterCloud["1"] = CharacterFullInfoCloud.Base(
                "1",
                "1",
                "https://swapi.dev/api/people/1",
                "https://swapi.dev/api/planets/1/",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            mutableMapOfCharacterCloud["2"] = CharacterFullInfoCloud.Base(
                "1",
                "1",
                "https://swapi.dev/api/people/2",
                "https://swapi.dev/api/planets/2/",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            mutableMapOfCharacterCloud["3"] = CharacterFullInfoCloud.Base(
                "1",
                "1",
                "https://swapi.dev/api/people/3",
                "https://swapi.dev/api/planets/2/",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            mutableMapOfCharacterCloud["4"] = CharacterFullInfoCloud.Base(
                "1",
                "1",
                "https://swapi.dev/api/people/4",
                "https://swapi.dev/api/planets/1/",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            mutableMapOfCharacterCloud["5"] = CharacterFullInfoCloud.Base(
                "1",
                "1",
                "https://swapi.dev/api/people/5",
                "https://swapi.dev/api/planets/1/",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
        }

    }

    override suspend fun getCharacterById(id: String) = handle {
        val result=mutableMapOfCharacterCloud[id]
        println("cloud $result")
        return@handle result?: throw UnknownHostException()
    }

}