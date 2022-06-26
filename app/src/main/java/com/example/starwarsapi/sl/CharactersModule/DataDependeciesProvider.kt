package com.example.starwarsapi.sl.CharactersModule

import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.sl.DataSourceProvider

interface DataDependeciesProvider {

    fun provideCharacterFullIndoRepository(): CharacterFullIInfoRepository

    class Base(
        private val characterFullInfoCacheDataSourceProvider: DataSourceProvider<CharacterFullInfoCacheDataSource.Mutable>,
        private val characterFullInfoCloudDataSourceProvider: DataSourceProvider<CharacterFullInfoCloudDataSource>
    ) :
        DataDependeciesProvider {


        override fun provideCharacterFullIndoRepository() = CharacterFullIInfoRepository.Base(
            characterFullInfoCacheDataSourceProvider.provideDataSource(),
            CharacterCache.Mapper.CharacterToCharacterFullUI(),
            characterFullInfoCloudDataSourceProvider.provideDataSource(),
            CharacterFullInfoCloud.Mapper.BaseToCharacterCache(UrlIdMapper.IdConverter())
        )


    }
}