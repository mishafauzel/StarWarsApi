package com.example.starwarsapi.data.character.cloud

import retrofit2.http.GET
import retrofit2.http.Path

interface FullInfoCharacter {

    @GET("people/{id}")
    suspend fun getCharacterById(@Path(value = "id") id: String): CharacterFullInfoCloud.Base

}