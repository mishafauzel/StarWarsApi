package com.example.starwarsapi.sl

import com.example.starwarsapi.data.cache.PlanetsCacheDataSourceTest
import com.example.starwarsapi.domain.HandleDomainException
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.LiveDataTransformator
import com.example.starwarsapi.presentation.ManageResourceTest
import com.example.starwarsapi.presentation.character.TestCommunication
import com.example.starwarsapi.presentation.main.UnsupportedErrorCommunication
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.sl.CharactersModule.CharacterModule
import com.example.starwarsapi.sl.PlanetsModule.PlanetsAndCharactersModule
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.example.starwarsapi.sl.base.MainNavigationSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication

interface ViewModelsSource {
    fun provideCharacterModule(
        isInternetAvailable: Boolean,
        islocalDataAvailable: Boolean,
        dispatchers: Dispatchers,
        testCommunication: TestCommunication,
        getInfoCommunication: GetInfoCommunication = GetInfoCommunication.Base()
    ): CharacterModule

    fun providePlanetsModule(
        dispatchers: Dispatchers,
        isLocalDataAvailable: Boolean,
        isInternetAvailable: Boolean,
        liveDataTransformator: LiveDataTransformator<PlanetsUi, PlanetsUi>,
        testCommunication: com.example.starwarsapi.presentation.planets.TestCommunication
    ): PlanetsAndCharactersModule

    class Base() : ViewModelsSource {


        override fun provideCharacterModule(
            isInternetAvailable: Boolean,
            islocalDataAvailable: Boolean,
            dispatchers: Dispatchers,
            testCommunication: TestCommunication,
            getInfoCommunication: GetInfoCommunication
        ): CharacterModule {
            val dataQueueSource = MainDataQueueSource()
            dataQueueSource.provideDataQueue().update(1)
            val manageResources = ManageResourceTest()
            val handleError = HandleDomainException()
            return CharacterModule(
                dispatchers,
                CanGoBack.Callback.Base(),
                UnsupportedErrorCommunication(GlobalErrorCommunication.Base(), manageResources),
                ProgressCommunication.Base(),
                dataQueueSource,
                TestCharacterFullInfoLocalDataSourceProvider(islocalDataAvailable),
                TestCharacterFullInfoRemoteDataSourceProvider(handleError, isInternetAvailable),
                manageResources,
                testCommunication
            )
        }


        override fun providePlanetsModule(
            dispatchers: Dispatchers,
            isLocalDataAvailable: Boolean,
            isInternetAvailable: Boolean,
            liveDataTransformator: LiveDataTransformator<PlanetsUi, PlanetsUi>,
            testCommunication: com.example.starwarsapi.presentation.planets.TestCommunication
        ): PlanetsAndCharactersModule {
            val manageResourceTest = ManageResourceTest()
            val handleError = HandleDomainException()
            return PlanetsAndCharactersModule(
                CanGoBack.Callback.Base(),
                MainDataQueueSource(),
                MainNavigationSource(),
                ProgressCommunication.Base(),
                dispatchers,
                manageResourceTest,
                TestPlanetsCacheDataProvider(isLocalDataAvailable),
                TestPlanetsCloudDataSourceProvider(handleError, isInternetAvailable),
                TestCharacterCacheDataProvider(isLocalDataAvailable),
                TestCharacterCloudDataSourceProvider(handleError, isInternetAvailable),
                UnsupportedErrorCommunication(GlobalErrorCommunication.Base(), manageResourceTest),
                testCommunication,liveDataTransformator
            )
        }
    }

}