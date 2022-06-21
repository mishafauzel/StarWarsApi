package com.example.starwarsapi.data.planets.cache.planets

class TestPlanetsCacheDataSource(
    private val dataSource: MutableMap<Int, List<PlanetCache>>,
    private val mapperPlanetCacheToPageNumber: PlanetCache.Mapper<Int>
) : PlanetCacheDataSource.Mutable {

    override fun save(data: List<PlanetCache>) {
        val page = data.first().map(mapperPlanetCacheToPageNumber)
        dataSource[page] = data
    }

    override fun read(inputData: Int): PlanetsCache {
        return PlanetsCache.Base(dataSource[inputData] ?: emptyList())
    }

}