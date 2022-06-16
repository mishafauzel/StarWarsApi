package com.example.starwarsapi.domain.characters

import android.util.Log
import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.presentation.character.CharacterFullUI
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor

interface CharacterInteractor {
    suspend fun getListOfPlanetsByPage(
        id: Int, atFinish: () -> Unit,
        successful: (CharacterFullUI) -> Unit
    )

    class Base(
        private val characterFullIInfoRepository: CharacterFullIInfoRepository,
         handleError: HandleError,
        dispatchers: Dispatchers
    ) : CharacterInteractor,
        Interactor.Abstract(dispatchers, handleError) {
        override suspend fun getListOfPlanetsByPage(
            id: Int,
            atFinish: () -> Unit,
            successful: (CharacterFullUI) -> Unit
        ) =handle(successful=successful,atFinish=atFinish){
            val result=characterFullIInfoRepository.getFullInfoAboutCharacter(id)
            Log.d("tag", "getListOfPlanetsByPage: $result")
            return@handle result
        }


    }
}