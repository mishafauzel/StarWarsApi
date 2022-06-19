package com.example.starwarsapi.data.character

import com.example.starwarsapi.data.character.cache.CharacterCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI

interface CharacterFullIInfoRepository {

    suspend fun getFullInfoAboutCharacter(id: Int): CharacterFullUI

    class Base(
        private val characterCacheDataSource: CharacterCacheDataSource.Mutable,
        private val characterCacheToCharacterFullUIMapper: CharacterCache.Mapper<CharacterFullUI>,
        private val characterService: CharacterFullInfoCloudDataSource,
        private val characterCloudToCharacterFullCacheMapper: CharacterFullInfoCloud.Mapper<CharacterCache>
    ) : CharacterFullIInfoRepository {

        override suspend fun getFullInfoAboutCharacter(id: Int): CharacterFullUI {
            val character = characterCacheDataSource.read(id)
            return if (character.hasExtraData()) {
                character.map(characterCacheToCharacterFullUIMapper)
            } else {
                val characterCloud = characterService.getCharacterById(id.toString())
                val charCache = characterCloud.map(characterCloudToCharacterFullCacheMapper)
                characterCacheDataSource.save(charCache)
                charCache.map(characterCacheToCharacterFullUIMapper)
            }

        }
    }
}
