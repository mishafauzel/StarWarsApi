package com.example.starwarsapi.data.character.cloud

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface CharacterFullInfoCloudDataSource : CloudDataSource {

    suspend fun getCharacterById(id: String): CharacterFullInfoCloud.Base

    class Base(private val fullInfoCharacter: FullInfoCharacter, handleError: HandleError) :
        CharacterFullInfoCloudDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getCharacterById(id: String) = handle {
            return@handle fullInfoCharacter.getCharacterById(id)
        }


    }
}