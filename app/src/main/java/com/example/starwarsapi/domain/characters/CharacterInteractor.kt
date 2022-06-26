package com.example.starwarsapi.domain.characters

import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor


interface CharacterInteractor {

    suspend fun getCharacterFullInfo(
        atFinish: () -> Unit,
        successful: (CharacterFullUI) -> Unit
    )

    class Base(
        private val characterFullIInfoRepository: CharacterFullIInfoRepository,
        private val idOfCharacter:Int,
        dispatchers: Dispatchers,
        errorCommunication: HandleError
    ) : CharacterInteractor,Interactor.Abstract(dispatchers, errorCommunication) {

        override suspend fun getCharacterFullInfo(
            atFinish: () -> Unit,
            successful: (CharacterFullUI) -> Unit
        )=handle(atFinish=atFinish, successful = successful) {
                return@handle characterFullIInfoRepository.getFullInfoAboutCharacter(idOfCharacter)

        }

    }
}
