package com.example.starwarsapi.presentation.planets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarsapi.presentation.ListTransformatorTest
import com.example.starwarsapi.presentation.TestDispatcher
import com.example.starwarsapi.presentation.planets.TestCommunication
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.sl.ViewModelsSource
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PlanetsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun `get list of planets and characters from internet,and empty database`() = runTest{
        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val testCommunication = TestCommunication()
        val listTransformatorTest=ListTransformatorTest(PlanetsUi.Mapper.Base())
        ViewModelsSource.Base().providePlanetsModule(testDispatcher,false,true,listTransformatorTest,testCommunication).viewModel()
        advanceUntilIdle()
        assert(testCommunication.checkValue().map(PlanetsUi.Mapper.Base()).size==7)
    }

    @Test
    fun `get list of planets and characters with no internet and empty database`()= runTest {
        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val testCommunication = TestCommunication()
        val listTransformatorTest=ListTransformatorTest(PlanetsUi.Mapper.Base())
        ViewModelsSource.Base().providePlanetsModule(testDispatcher,false,false,listTransformatorTest,testCommunication).viewModel()
        advanceUntilIdle()
        assert(testCommunication.checkValue().map(PlanetsUi.Mapper.Base()).size==1)
    }
    @Test
    fun `get list of planets and characters with no internet and something in database`()= runTest {
        val standartTestDispatcher = StandardTestDispatcher()
        kotlinx.coroutines.Dispatchers.setMain(standartTestDispatcher)
        val testDispatcher = TestDispatcher(this.coroutineContext)
        val testCommunication = TestCommunication()
        val listTransformatorTest=ListTransformatorTest(PlanetsUi.Mapper.Base())
        ViewModelsSource.Base().providePlanetsModule(testDispatcher,true,false,listTransformatorTest,testCommunication).viewModel()
        advanceUntilIdle()

        assert(testCommunication.checkValue().map(PlanetsUi.Mapper.Base()).size==7)
    }
}