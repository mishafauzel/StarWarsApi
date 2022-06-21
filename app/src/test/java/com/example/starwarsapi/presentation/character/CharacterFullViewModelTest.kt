package com.example.starwarsapi.presentation.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.example.starwarsapi.data.character.CharacterFullIInfoRepository
import com.example.starwarsapi.data.character.cache.CharacterCacheDataSource
import com.example.starwarsapi.data.character.cloud.CharacterFullInfoCloud
import com.example.starwarsapi.data.planets.BaseCharacterRepository
import com.example.starwarsapi.data.planets.UrlIdMapper
import com.example.starwarsapi.data.planets.cache.characters.CharacterCache
import com.example.starwarsapi.domain.DomainException
import com.example.starwarsapi.domain.HandleDomainException
import com.example.starwarsapi.domain.characters.CharacterInteractor
import com.example.starwarsapi.presentation.character.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.example.starwarsapi.presentation.character.items.CharacterFullInfoItem
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.sl.CoreModule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.coroutines.CoroutineContext

class CharacterFullViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun `test get full info about character with internet, and no data in database`() = runTest() {
        val characterCacheDataSource =
            CharacterCacheDataSourceTest(CharacterCache.Mapper.CharacterToIdMapper())
        characterCacheDataSource.save(CharacterCache.Base(1, 1, "1", "1"))
        val characterCloud = CharacterCloudDataSourceTest(HandleDomainException(), true)
        val charactersRepository = CharacterFullIInfoRepository.Base(
            characterCacheDataSource,
            CharacterCache.Mapper.CharacterToCharacterFullUI(),
            characterCloud,
            CharacterFullInfoCloud.Mapper.BaseToCharacterCache(UrlIdMapper.IdConverter())
        )
        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val retryCommunication = RetryCommunication.Base()
        val testCommunication = TestCommunication()
        val characterInteractor = CharacterInteractor.Base(
            charactersRepository,
            testDispatcher,
            DomainException.Mapper.BaseToCharacterUi(ManageResourceTest(), retryCommunication)
        )
        val characterFullViewModel = CharacterFullViewModel(
            CanGoBack.Callback.Base(),
            characterInteractor,
            ProgressCommunication.Base(),
            retryCommunication,
            testCommunication,
            testDispatcher,
            1
        )
        advanceUntilIdle()
        assert(
            testCommunication.checkValue(
                CharacterFullUI.Base(
                    listOf(
                        CharacterFullInfoItem(
                            "1",
                            "1",
                            1,
                            "1",
                            "1",
                            "1",
                            "1",
                            "1"
                        )
                    )
                )
            )
        )
    }
}