package com.example.starwarsapi.domain.planets

interface CharacterRepository {
    suspend fun selectCharacters(planetId:Int):List<CharacterDomain>

}