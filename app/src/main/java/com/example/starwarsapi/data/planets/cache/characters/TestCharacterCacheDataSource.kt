package com.example.starwarsapi.data.planets.cache.characters

class TestCharacterCacheDataSource(
    private val mutableListOfCharacters: MutableMap<Int, List<CharacterCache>>,
    private val mapperCharacterCacheToId: CharacterCache.Mapper<String>
) : CharactersCacheDataSource.Mutable {

    override fun save(data: List<CharacterCache>) {
        val id = data[0].map(mapperCharacterCacheToId).toInt()
        mutableListOfCharacters[id] = data
    }

    override fun read(inputData: Int): CharactersCache {
        return CharactersCache.Base(mutableListOfCharacters[inputData] ?: emptyList())
    }
}