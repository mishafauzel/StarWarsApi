package com.example.starwarsapi.data.character

import android.util.Log
import com.example.starwarsapi.data.character.cache.CharacterCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.character.cloud.FullInfoCharacter
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharacterDao
import com.example.starwarsapi.data.planets.cache.characters.CharactersCache
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloud
import com.example.starwarsapi.data.planets.cloud.characters.CharacterService
import com.example.starwarsapi.presentation.character.CharacterFullInfoItem
import com.example.starwarsapi.presentation.character.CharacterFullUI

interface CharacterFullIInfoRepository {
    suspend fun getFullInfoAboutCharacter(id: Int): CharacterFullUI
    class Base(
        private val characterCacheDataSource: CharacterCacheDataSource.Mutable,
        private val characterCacheToCharacterFullUIMapper: CharacterCache.Mapper<CharacterFullUI>,
        private val characterService: FullInfoCharacter,
        private val characterCloudToCharacterFullCacheMapper: CharacterFullInfoCloud.Mapper<CharacterCache>
    ) : CharacterFullIInfoRepository {
        override suspend fun getFullInfoAboutCharacter(id: Int): CharacterFullUI {
                val character = characterCacheDataSource.read(id)
                if (character.hasExtraData()) {
                    return character.map(characterCacheToCharacterFullUIMapper)
                } else {
                    val characterCloud = characterService.getCharacterById(id.toString())
                    Log.d("Tag", "getFullInfoAboutCharacter:$characterCloud ")
                    val charCache = characterCloud.map(characterCloudToCharacterFullCacheMapper)
                    characterCacheDataSource.save(charCache)
                    Log.d("Tag", "getFullInfoAboutCharacter:$charCache ")
                    return charCache.map(characterCacheToCharacterFullUIMapper)
                }
            }
        }
        }
