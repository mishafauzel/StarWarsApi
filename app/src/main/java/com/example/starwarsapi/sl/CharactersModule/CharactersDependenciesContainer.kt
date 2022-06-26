package com.example.starwarsapi.sl.CharactersModule

import androidx.lifecycle.ViewModel
import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.presentation.main.UnsupportedErrorCommunication
import com.example.starwarsapi.sl.DataSourceProvider
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.DependencyContainer

class CharactersDependenciesContainer(
    private val dependencyContainer: DependencyContainer,
    dispatchers: Dispatchers,
    canGoBack: CanGoBack.Callback,
    unsupportedErrorCommunication: UnsupportedErrorCommunication,
    manageResources: ManageResources,
    progressCommunication: ProgressCommunication.Base,
    mainDataQueueSource: MainDataQueueSource,
    characterFullInfoCacheDataSource: DataSourceProvider<CharacterFullInfoCacheDataSource.Mutable>,
    characterCloudDataSource:DataSourceProvider<CharacterFullInfoCloudDataSource>
) : DependencyContainer {

    private val characterModule by lazy { CharacterModule(dispatchers,canGoBack,unsupportedErrorCommunication,progressCommunication,mainDataQueueSource,characterFullInfoCacheDataSource,characterCloudDataSource,manageResources) }


    override fun <T : ViewModel> module(clazz: Class<T>) =
        if (clazz == CharacterFullViewModel::class.java)
            characterModule
        else
            dependencyContainer.module(clazz)
}