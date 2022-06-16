package com.example.starwarsapi.data.planets.cache.characters

import com.example.starwarsapi.core.IsDataFull
import com.example.starwarsapi.core.IsEmpty
import com.example.starwarsapi.domain.planets.CharacterDomain

interface CharactersCache : IsEmpty, IsDataFull {
    fun <T> map(mapper: Mapper<T>): T
    class Base(private val list: List<CharacterCache>) : CharactersCache {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(list)
        }

        override fun isEmpty(): Boolean {
            return list.isEmpty()
        }

        override fun isFull(): Boolean {
            return list.first().isFull()
        }
    }

    interface Mapper<T> {
        fun map(list: List<CharacterCache>): T
        class BaseToList() : Mapper<List<CharacterCache>> {
            override fun map(list: List<CharacterCache>): List<CharacterCache> {
                return list
            }
        }

        class BaseToListCharactersDomain(private val mapper: CharacterCache.Mapper<CharacterDomain>) :
            Mapper<List<CharacterDomain>> {
            override fun map(list: List<CharacterCache>): List<CharacterDomain> {
                return list.map {
                    it.map(mapper)
                }
            }

        }

        class BaseToListOfIds(private val characterToUrlMapper: CharacterCache.Mapper<String>) :
            Mapper<List<String>> {
            override fun map(list: List<CharacterCache>): List<String> {
                return list.map { characterCache ->
                    characterCache.map(characterToUrlMapper)

                }
            }

        }

    }

}
