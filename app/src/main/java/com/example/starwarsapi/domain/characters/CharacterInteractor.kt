package com.example.starwarsapi.domain.characters

import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.github.johnnysc.coremvvm.core.Dispatchers

interface CharacterInteractor {

    suspend fun getCharacterFullInfo(
        id: Int, atFinish: () -> Unit,
        successful: (CharacterFullUI) -> Unit
    )

    class Base(
        private val characterFullIInfoRepository: CharacterFullIInfoRepository,
        private val dispatchers: Dispatchers,
        private val mapperFactoryDomainExceptionMapperToListOfItemsUiMapper: DomainException.Mapper<CharacterFullUI>
    ) : CharacterInteractor {

        override suspend fun getCharacterFullInfo(
            id: Int,
            atFinish: () -> Unit,
            successful: (CharacterFullUI) -> Unit
        ) {
            try {
                val result = characterFullIInfoRepository.getFullInfoAboutCharacter(id)
                dispatchers.changeToUI { successful.invoke(result) }
            } catch (domainEx: DomainException.AbstractDomainException) {
                dispatchers.changeToUI {
                    successful.invoke(
                        domainEx.map(
                            mapperFactoryDomainExceptionMapperToListOfItemsUiMapper
                        )
                    )
                }
            } finally {
                dispatchers.changeToUI { atFinish.invoke() }
            }
        }

    }
}
