package com.example.starwarsapi.data.cache

import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource

class CharacterCacheDataSourceTest( isAvailable: Boolean) :
    CharactersCacheDataSource.Mutable {
    private val mutableMap = mutableMapOf<Int, List<CharacterCache>>()

    init {
        if (isAvailable) {
            mutableMap[1] = listOf(
                CharacterCache.Base(1, 1, "1", "1"),
                CharacterCache.Base(2, 1, "2", "2")
            )

            mutableMap[2] = listOf(
                CharacterCache.Base(3, 2, "3", "3"),
                CharacterCache.Base(4, 2, "4", "4")
            )

            mutableMap[3] = listOf(
                CharacterCache.Base(5, 3, "5", "5"),
                CharacterCache.Base(6, 3, "6", "6")
            )

            mutableMap[4] = listOf(
                CharacterCache.Base(7, 4, "7", "7"),
                CharacterCache.Base(8, 4, "8", "8")
            )

        }
    }

    override fun save(data: List<CharacterCache>) {
        println(data)
        val firstValue=data.firstOrNull()
        if(firstValue==null)
        {}
        else{
        var planetId:Int =firstValue.map(CharacterCacheToIdOfPlanet())
        var mutableList = mutableListOf<CharacterCache>()
            for (character in data) {
                val curPlanetId= character.map(CharacterCacheToIdOfPlanet())
                if(planetId==curPlanetId)
                {
                    mutableList.add(character)
                }
                else
                {
                    mutableMap[planetId]=mutableList
                    planetId=curPlanetId
                    mutableList= mutableListOf()
                    mutableList.add(character)
                }
            }
            mutableMap[planetId]=mutableList
        }
        println("mutableMap $mutableMap")
    }

    override fun read(inputData: Int): CharactersCache {
        val result = mutableMap[inputData]
        if (result == null) {
            return CharactersCache.Base(emptyList())
        } else
            return CharactersCache.Base(result)
    }
}

private class CharacterCacheToIdOfPlanet : CharacterCache.Mapper<Int> {
    override fun map(
        id: Int,
        planetId: Int,
        characterName: String,
        birthYear: String,
        hairColor: String,
        skinColor: String,
        gender: String,
        mass: String,
        height: String
    ): Int {
        return planetId
    }
}