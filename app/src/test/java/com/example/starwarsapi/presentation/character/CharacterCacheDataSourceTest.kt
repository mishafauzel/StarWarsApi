package com.example.starwarsapi.presentation.character

import com.example.starwarsapi.data.character.cache.CharacterCacheDataSource
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache

class CharacterCacheDataSourceTest(private val mapper:CharacterCache.Mapper<String>):CharacterCacheDataSource.Mutable {

    private val mutableMapOfCharacters= mutableMapOf<Int,CharacterCache>()

    override fun save(data: CharacterCache) {
        val id=data.map(mapper)
        println("save $data,$id")
        mutableMapOfCharacters[id.toInt()]=data
    }

    override fun read(inputData: Int) = mutableMapOfCharacters[inputData]!!

    fun printData()
    {
        println("local data $mutableMapOfCharacters")
    }
}

