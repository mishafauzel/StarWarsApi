package com.example.starwarsapi.data.planets.cloud.characters

import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cloud.characters.CharactersCloud.Mapper.Base
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud.Mapper as CharacterMapper
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud.Mapper.Factory as CharacterMapperFactory

interface CharactersCloud {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val listOfCharacters: List<CharacterCloud>) : CharactersCloud {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(listOfCharacters)

    }

    interface Mapper<T> {

        fun map(listOfCharacters: List<CharacterCloud>): T

        class Base(
            private val characterCloudMapperFac: CharacterMapperFactory<CharacterMapper<CharacterCache>, Int>,
            private val id: Int
        ) : Mapper<CharactersCache> {

            override fun map(listOfCharacters: List<CharacterCloud>): CharactersCache {
                val characterMapper = characterCloudMapperFac.create(id)
                return CharactersCache.Base(listOfCharacters.map { characterCloud ->
                    characterCloud.map(characterMapper)
                })
            }

        }

        interface Factory<T : Mapper<*>, B> {

            fun create(arg: B): T

            class Base(private val characterCloudMapperFac: CharacterMapperFactory<CharacterMapper<CharacterCache>, Int>) :
                Factory<Mapper<CharactersCache>, Int> {

                override fun create(arg: Int) = Base(characterCloudMapperFac, arg)

            }
        }
    }
}