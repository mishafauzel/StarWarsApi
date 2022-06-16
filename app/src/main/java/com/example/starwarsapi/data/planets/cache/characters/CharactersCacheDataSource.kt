package com.example.starwarsapi.data.planets.cache.characters

interface CharactersCacheDataSource {
    interface Save : com.github.johnnysc.coremvvm.core.Save<List<CharacterCache>>

    interface Read : com.example.starwarsapi.core.Read<Int, CharactersCache>

    interface Mutable : Save, Read

    class Base(private val characterDao: CharacterDao) : Mutable {
        override fun save(data: List<CharacterCache>) {
            characterDao.insertListOfCharacters(data.map { it as CharacterCache.Base })
        }

        override fun read(inputData: Int): CharactersCache {
            return CharactersCache.Base(characterDao.selectCharByPlanetId(inputData))
        }
    }

}