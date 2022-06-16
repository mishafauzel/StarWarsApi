package com.example.starwarsapi.presentation.planets

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.core.Retry
import com.example.starwarsapi.domain.planets.PlanetsInteractor
import com.example.starwarsapi.presentation.planets.items.SomethingWentWrongItem
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsErrorComunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.*
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

private const val TAG = "PlanetsViewModel"
class PlanetsViewModel(canGoBackCallback: CanGoBack.Callback,
                       private val interactor: PlanetsInteractor,
                       private val planetsErrorCommunication: PlanetsErrorComunication.Observe,
                       private val progressCommunication: ProgressCommunication.Update,
                       private val nextPageCommunication: NextPageCommunication.Base,
                       private val listMutator: ListMutator,
                       private val mapperPagerDataToInt:PagerData.Mapper<Int>,

                       communication: PlanetsCommunication,
                       dispatchers: Dispatchers
):BackPress.ViewModel<PlanetsUi>(canGoBackCallback,communication,dispatchers),Retry {
    private var currentPageData:PagerData=PagerData.Base(0,1)
    private var canGoBack = true

    init {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        nextPageCommunication.map(currentPageData)

        listMutator.setTransformable(communication)
    }

    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }



    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }



    fun observeException(lifecycleOwner: LifecycleOwner,observer: Observer<Int>)
    {
        planetsErrorCommunication.observe(lifecycleOwner,observer)
    }
    fun addSomethingWentWrong()
    {
        Log.d(TAG, "addSomethingWentWrong: ")
        communication.map(PlanetsUi.Base(planetUI = listOf(SomethingWentWrongItem(retry = this))))
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)

    fun observeNextPageCommunication(lifecycleOwner: LifecycleOwner,observer: Observer<PagerData>)
    {
        nextPageCommunication.observe(lifecycleOwner,observer)
    }
    fun getInfoNextPage(pageNumber:PagerData)
    {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
            interactor.getListOfPlanetsByPage(pageNumber.map(mapperPagerDataToInt),atFinish) {
                communication.map(it.first)
                currentPageData=it.second

            }
        }
    }

    override fun retry() {
        Log.d(TAG, "retry: $currentPageData")
      getInfoNextPage(currentPageData)
    }

    fun observeListMutator(owner: LifecycleOwner,observer:Observer<PlanetsUi>)
    {
        this.listMutator.observe(owner,observer)
    }


}