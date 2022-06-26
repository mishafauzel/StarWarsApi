package com.example.starwarsapi.sl

import com.example.starwarsapi.data.planets.cache.planets.PlanetCacheDataSource

/**
 * out-type of providing data source
 */
interface DataSourceProvider<out> {
    fun provideDataSource():out

}