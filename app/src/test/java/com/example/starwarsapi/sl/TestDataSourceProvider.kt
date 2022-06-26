package com.example.starwarsapi.sl

import android.provider.ContactsContract
import com.example.starwarsapi.data.cache.CharacterCacheDataSourceTest
import com.example.starwarsapi.data.cache.CharacterFullInfoCacheDataSourceTest
import com.example.starwarsapi.data.cache.PlanetsCacheDataSourceTest
import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.data.cloud.CharacterCloudDataSourceTest
import com.example.starwarsapi.data.cloud.CharacterFullInfoCloudDataSourceTest
import com.example.starwarsapi.data.cloud.PlanetsCloudDataSourceTest
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.data.planets.cache.characters.CharactersCacheDataSource
import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource
import com.example.starwarsapi.data.planets.cloud.characters.CharacterCloudDataSource
import com.example.starwarsapi.data.planets.cloud.planets.PlanetsCloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError


class TestCharacterFullInfoLocalDataSourceProvider(private val isAvailable: Boolean) :
    DataSourceProvider<CharacterFullInfoCacheDataSource.Mutable> {
    override fun provideDataSource(): CharacterFullInfoCacheDataSource.Mutable {
        return CharacterFullInfoCacheDataSourceTest(
            CharacterCache.Mapper.CharacterToIdMapper(),
            isAvailable
        )
    }
}

class TestCharacterFullInfoRemoteDataSourceProvider(
    private val handleError: HandleError,
    private val isAvailable: Boolean
) : DataSourceProvider<CharacterFullInfoCloudDataSource> {
    override fun provideDataSource(): CharacterFullInfoCloudDataSource {
        return CharacterFullInfoCloudDataSourceTest(handleError, isAvailable)
    }
}

class TestCharacterCloudDataSourceProvider(
    private val handleError: HandleError,
    private val isAvailable: Boolean
) : DataSourceProvider<CharacterCloudDataSource> {
    override fun provideDataSource(): CharacterCloudDataSourceTest {
        return CharacterCloudDataSourceTest(isAvailable, handleError)
    }
}

class TestPlanetsCloudDataSourceProvider(
    private val handleError: HandleError,
    private val isAvailable: Boolean
) : DataSourceProvider<PlanetsCloudDataSource> {
    override fun provideDataSource(): PlanetsCloudDataSourceTest {
        return PlanetsCloudDataSourceTest(isAvailable, handleError)
    }
}

class TestCharacterCacheDataProvider(private val isAvailable: Boolean) :
    DataSourceProvider<CharactersCacheDataSource.Mutable> {
    private val characterCacheDataSourceTest=CharacterCacheDataSourceTest(isAvailable)

    override fun provideDataSource(): CharacterCacheDataSourceTest {
        return characterCacheDataSourceTest
    }
}

class TestPlanetsCacheDataProvider(private val isAvailable: Boolean) :
    DataSourceProvider<PlanetCacheDataSource.Mutable> {
    override fun provideDataSource(): PlanetsCacheDataSourceTest {
        return PlanetsCacheDataSourceTest(isAvailable)
    }

}