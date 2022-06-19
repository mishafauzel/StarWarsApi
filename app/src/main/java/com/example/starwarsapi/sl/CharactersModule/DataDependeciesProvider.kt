package com.example.starwarsapi.sl.CharactersModule

import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.data.character.cache.CharacterCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.domain.HandleDomainException
import com.github.johnnysc.coremvvm.sl.CoreModule

interface DataDependeciesProvider {

    fun provideCharacterFullIndoRepository(): CharacterFullIInfoRepository

    class Base(coreModule: CoreModule, private val provideServices: ProvideServices) :
        DataDependeciesProvider {
        private val appDatabase = coreModule.provideRoomDatabase(AbstractDatabase::class.java)
        private val handleException = HandleDomainException()
        override fun provideCharacterFullIndoRepository() = CharacterFullIInfoRepository.Base(
            CharacterCacheDataSource.Base(appDatabase.provideCharactersDao()),
            CharacterCache.Mapper.CharacterToCharacterFullUI(),
            CharacterFullInfoCloudDataSource.Base(
                provideServices.provideFullInfoCharacter(),
                handleException
            ),
            CharacterFullInfoCloud.Mapper.BaseToCharacterCache(UrlIdMapper.IdConverter())
        )


    }
}