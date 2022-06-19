package com.example.starwarsapi.sl.CharactersModule

import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.presentation.character.base_communications.RetryCommunication
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.sl.CoreModule

interface DomainDependensiesProvider {

    fun provideCharacterInteractor(): CharacterInteractor

    class Base(
        private val dataDependeciesProvider: DataDependeciesProvider,
        private val coreModule: CoreModule,
        private val manageResources: ManageResources,
        private val retryCommunication: RetryCommunication.Base
    ) : DomainDependensiesProvider {

        override fun provideCharacterInteractor() = CharacterInteractor.Base(
            dataDependeciesProvider.provideCharacterFullIndoRepository(), coreModule.dispatchers(),
            DomainException.Mapper.BaseToCharacterUi(manageResources, retryCommunication)
        )

    }
}

