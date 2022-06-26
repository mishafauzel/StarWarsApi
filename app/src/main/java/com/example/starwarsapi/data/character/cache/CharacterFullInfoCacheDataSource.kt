package com.example.starwarsapi.data.character.cache

import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharacterDao

interface CharacterFullInfoCacheDataSource {

    interface Save : com.github.johnnysc.coremvvm.core.Save<CharacterCache>

    interface Read : com.example.starwarsapi.core.Read<Int, CharacterCache>

    interface Mutable : Save, Read

    class Base(private val characterDao: CharacterDao) : Mutable {

        override fun save(data: CharacterCache) {
            characterDao.insertCharacter(data as CharacterCache.Base)
        }

        override fun read(inputData: Int): CharacterCache {
            return characterDao.selectCharById(inputData)
        }
    }
}