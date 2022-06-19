package com.example.starwarsapi.data.planets.cloud

import com.example.starwarsapi.data.character.cloud.FullInfoCharacter
import com.example.starwarsapi.data.planets.cloud.characters.CharacterService
import com.example.starwarsapi.data.planets.cloud.planets.PlanetService
import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder

interface ProvideServices {

    fun provideCharacterService(): CharacterService

    fun providePlanetsService(): PlanetService

    fun provideFullInfoCharacter(): FullInfoCharacter

    class Base(retrofitBuilder: ProvideRetrofitBuilder) :
        MakeService.Abstract(retrofitBuilder), ProvideServices {

        override fun baseUrl() = "https://swapi.dev/api/"

        override fun provideCharacterService(): CharacterService {
            return service(CharacterService::class.java)
        }

        override fun providePlanetsService(): PlanetService {
            return service(PlanetService::class.java)
        }

        override fun provideFullInfoCharacter(): FullInfoCharacter {
            return service(FullInfoCharacter::class.java)
        }
    }
}