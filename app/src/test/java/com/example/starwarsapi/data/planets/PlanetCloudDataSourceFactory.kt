//package com.example.starwarsapi.data.planets
//
//import com.example.starwarsapi.data.planets.cloud.planets.TestPlanetCloudDataSource
//
//class PlanetCloudDataSourceFactory {
//    fun createPlanetsCloudDataSource(state: TestPlanetCloudDataSource.State): TestPlanetCloudDataSource
//    {
//        return when(state)
//        {
//            is TestPlanetCloudDataSource.State.Empty->
//                TestPlanetCloudDataSource()
//            is(TestPlanetCloudDataSource.State.HasSomeInfo)->
//                 TestPlanetCloudDataSource()
//
//        }
//    }
//}