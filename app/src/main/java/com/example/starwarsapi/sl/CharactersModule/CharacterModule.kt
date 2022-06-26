package com.example.starwarsapi.sl.CharactersModule

import com.example.starwarsapi.data.character.cache.CharacterFullInfoCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloudDataSource
import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.character.CharacterFullViewModel
import com.example.starwarsapi.presentation.character.base_communications.CharacterFullCommunication
import com.example.starwarsapi.presentation.character.base_communications.ErrorCommunication
import com.example.starwarsapi.presentation.main.UnsupportedErrorCommunication
import com.example.starwarsapi.sl.DataSourceProvider
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.Module

class CharacterModule(

    private val dispatchers: Dispatchers,
    private val canGoBack: CanGoBack.Callback,
    private val unsupportedErrorCommunication: UnsupportedErrorCommunication,
    private val progressCommunication: ProgressCommunication.Base,
    private val dataQueueSource: MainDataQueueSource,
    private val charactersCacheDataSource: DataSourceProvider<CharacterFullInfoCacheDataSource.Mutable>,
    private val characterFullInfoCloudDataSource: DataSourceProvider<CharacterFullInfoCloudDataSource>,
    private val manageResources: ManageResources,
    private val characterFullInfoCommunication:CharacterFullCommunication=CharacterFullCommunication.Base(),
    private val getInfoCommunication:GetInfoCommunication=GetInfoCommunication.Base()
) : Module<CharacterFullViewModel> {

    override fun viewModel(): CharacterFullViewModel {
        val errorCommunication=ErrorCommunication(unsupportedErrorCommunication,characterFullInfoCommunication,DomainException.Mapper.Factory.Base(manageResources,getInfoCommunication))
        val dataDependenciesProvider = DataDependeciesProvider.Base(charactersCacheDataSource,characterFullInfoCloudDataSource)

        val domainDependenciesProvider = DomainDependenciesProvider.Base(
            dataDependenciesProvider,
            dataQueueSource,
            dispatchers,
            errorCommunication
        )
        return CharacterFullViewModel(
            canGoBack,
            domainDependenciesProvider.provideCharacterInteractor(),
            progressCommunication,
            characterFullInfoCommunication,
            dispatchers,
            getInfoCommunication

        )
    }
}