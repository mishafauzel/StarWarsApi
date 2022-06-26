package com.example.starwarsapi.data.cache

import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache

class CharacterFullInfoCacheDataSourceTest(private val mapper:CharacterCache.Mapper<String>,private val hasLocalInfo:Boolean):CharacterFullInfoCacheDataSource.Mutable {

    private val mutableMapOfCharacters= mutableMapOf<Int,CharacterCache>()
    init {
        if(hasLocalInfo)
        {
            mutableMapOfCharacters[1] = CharacterCache.Base(
                1,
                1,
                "1",
                "1",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
            mutableMapOfCharacters[2] =  CharacterCache.Base(
                2,
                1,
                "2",
                "2",
                "1",
                "1",
                "1",
                "1",
                "1"
            )
        }
        else
        {
            mutableMapOfCharacters[1] = CharacterCache.Base(
                1,
                1,
                "name1",
                "https://swapi.dev/api/planets/1/",

            )
            mutableMapOfCharacters[2] =  CharacterCache.Base(
                2,
                1,
                "name2",
                "https://swapi.dev/api/planets/1/"
            )
        }
    }

    override fun save(data: CharacterCache) {
        val id=data.map(mapper)
        println("save $data,$id")
        mutableMapOfCharacters[id.toInt()]=data
    }

    override fun read(inputData: Int):CharacterCache {
        println("id")
        return mutableMapOfCharacters[inputData]!!}

    fun printData()
    {
        println("local data $mutableMapOfCharacters")
    }
}

