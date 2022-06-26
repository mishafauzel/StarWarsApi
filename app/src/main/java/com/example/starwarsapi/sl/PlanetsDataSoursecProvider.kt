package com.example.starwarsapi.sl.PlanetsModule

import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.data.planets.cache.AbstractDatabase
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cloud.ProvideServices
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.example.starwarsapi.sl.DataSourceProvider
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.sl.CoreModule

class BasePlanetsLocal(private val core: CoreModule) :
    DataSourceProvider<PlanetCacheDataSource.Mutable> {
    override fun provideDataSource(): PlanetCacheDataSource.Mutable {
        return PlanetCacheDataSource.Base(
            core.provideRoomDatabase(AbstractDatabase::class.java).providePlanetsDao()
        )
    }
}

class BasePlanetsRemote(
    private val provideServices: ProvideServices,
    private val handleDomainException: HandleError
) : DataSourceProvider<PlanetsCloudDataSource> {
    override fun provideDataSource(): PlanetsCloudDataSource {
        return PlanetsCloudDataSource.Base(
            provideServices.providePlanetsService(),
            handleDomainException
        )
    }
}

class BaseCharacterLocal(private val core: CoreModule) :
    DataSourceProvider<CharactersCacheDataSource.Mutable> {
    override fun provideDataSource(): CharactersCacheDataSource.Mutable {
        return CharactersCacheDataSource.Base(
            core.provideRoomDatabase(AbstractDatabase::class.java).provideCharactersDao()
        )
    }

}

class BaseCharacterRemote(
    private val provideServices: ProvideServices,
    private val handleDomainException: HandleError
) : DataSourceProvider<CharacterCloudDataSource> {
    override fun provideDataSource(): CharacterCloudDataSource {
        return CharacterCloudDataSource.Base(
            provideServices.provideCharacterService(),
            handleDomainException
        )
    }
}

class BaseCharacterFullInfoCacheDataSourceProvide(private val core: CoreModule):DataSourceProvider<CharacterFullInfoCacheDataSource.Mutable>
{
    override fun provideDataSource(): CharacterFullInfoCacheDataSource.Mutable {
        return CharacterFullInfoCacheDataSource.Base(core.provideRoomDatabase(AbstractDatabase::class.java).provideCharactersDao())
    }


}

class BaseCharacterFullInfoRemoteSource(
    private val provideServices: ProvideServices,
    private val handleDomainException: HandleError
) : DataSourceProvider<CharacterFullInfoCloudDataSource> {
    override fun provideDataSource(): CharacterFullInfoCloudDataSource {
        return CharacterFullInfoCloudDataSource.Base(
            provideServices.provideFullInfoCharacter(),
            handleDomainException
        )
    }

}