package com.example.starwarsapi.data.planets.cloud.characters

import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    suspend fun getCharactersById(listOfIds:List<String>): CharactersCloud
    {
        return CharactersCloud.Base(listOfIds.map { id ->
            getCharacterById(id)
        })
    }
    @GET("people/{id}")
    suspend fun getCharacterById(@Path(value = "id")id:String): CharacterCloud.Base
}