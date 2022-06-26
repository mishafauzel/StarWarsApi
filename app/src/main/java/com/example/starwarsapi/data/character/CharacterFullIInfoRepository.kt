package com.example.starwarsapi.data.character

import android.util.Log
import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import kotlin.math.log

private const val TAG = "CharacterFullIInfoRepos"
interface CharacterFullIInfoRepository {

    suspend fun getFullInfoAboutCharacter(id: Int): CharacterFullUI

    class Base(
        private val characterFullInfoCacheDataSource: CharacterFullInfoCacheDataSource.Mutable,
        private val characterCacheToCharacterFullUIMapper: CharacterCache.Mapper<CharacterFullUI>,
        private val characterService: CharacterFullInfoCloudDataSource,
        private val characterCloudToCharacterFullCacheMapper: CharacterFullInfoCloud.Mapper<CharacterCache>
    ) : CharacterFullIInfoRepository {

        override suspend fun getFullInfoAboutCharacter(id: Int): CharacterFullUI {
            println("so,ething")
            val character = characterFullInfoCacheDataSource.read(id)

            println(character)
            return if (character.hasExtraData()) {
                character.map(characterCacheToCharacterFullUIMapper)
            } else {
                val characterCloud = characterService.getCharacterById(id.toString())
                val charCache = characterCloud.map(characterCloudToCharacterFullCacheMapper)
                characterFullInfoCacheDataSource.save(charCache)
                charCache.map(characterCacheToCharacterFullUIMapper)
            }

        }
    }
}
