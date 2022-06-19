package com.example.starwarsapi.data.planets.cloud.characters

import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("people/{id}")
    suspend fun getCharacterById(@Path(value = "id") id: String): CharacterCloud.Base

}