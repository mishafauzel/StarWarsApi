package com.example.starwarsapi.presentation.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarsapi.presentation.GetInfoCommunication
import com.example.starwarsapi.presentation.TestDispatcher
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.example.starwarsapi.presentation.character.items.CharacterFullInfoItem
import com.example.starwarsapi.presentation.planets.items.SomethingWentWrongItem
import com.example.starwarsapi.sl.ViewModelsSource
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CharacterFullViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun `test get full info about character with internet, and no data in database`() = runTest() {

        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val testCommunication = TestCommunication()
        ViewModelsSource.Base()
            .provideCharacterModule(true, false, testDispatcher, testCommunication).viewModel()
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

    @Test
    fun `test get full info about character with no internet, and data in database`() = runTest {
        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val testCommunication = TestCommunication()
        ViewModelsSource.Base()
            .provideCharacterModule(false, true, testDispatcher, testCommunication).viewModel()
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
    @Test
    fun `test get full info about character with no internet, and no data in database`()=runTest {
        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val testCommunication = TestCommunication()
        val getInfoCommunication=GetInfoCommunication.Base()
        val expected= CharacterFullUI.Base(
            listOf(
                SomethingWentWrongItem("Some string from resources",getInfoCommunication)
            )
        )
        ViewModelsSource.Base()
            .provideCharacterModule(false, false, testDispatcher, testCommunication,getInfoCommunication).viewModel()
        advanceUntilIdle()
        assert(
            testCommunication.checkValue(
            expected
            ))

    }
}