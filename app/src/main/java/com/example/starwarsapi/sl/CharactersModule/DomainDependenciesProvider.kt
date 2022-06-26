package com.example.starwarsapi.sl.CharactersModule

import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.sl.base.MainDataQueueSource
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError

interface DomainDependenciesProvider {

    fun provideCharacterInteractor(): CharacterInteractor

    class Base(
        private val dataDependeciesProvider: DataDependeciesProvider,
        private val mainDataQueueSource: MainDataQueueSource,
        private val dispatchers: Dispatchers,
        private val errorCommunication:HandleError
    ) : DomainDependenciesProvider {

        override fun provideCharacterInteractor() = CharacterInteractor.Base(
            dataDependeciesProvider.provideCharacterFullIndoRepository(),
            mainDataQueueSource.provideDataQueue().read() as Int,
            dispatchers,
            errorCommunication

        )

    }
}

