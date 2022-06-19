package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.core.Retry
import com.example.starwarsapi.domain.planets.PlanetsInteractor
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.base_communications.PlanetsCommunication
import com.example.starwarsapi.presentation.planets.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.CanGoBack
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility


class PlanetsViewModel(
    canGoBackCallback: CanGoBack.Callback,
    private val interactor: PlanetsInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    private val nextPageCommunication: NextPageCommunication.Base,
    private val retryCommunication: RetryCommunication.Observe,
    private val listMutator: ListMutator,
    communication: PlanetsCommunication,
    dispatchers: Dispatchers
) : BackPress.ViewModel<PlanetsUi>(canGoBackCallback, communication, dispatchers), Retry {

    private var firstPagerData: PagerData = PagerData.Base(0, 1)
    private var canGoBack = true
    private val atFinish = {
        progressCommunication.map(Visibility.Gone())
        canGoBack = true
    }
    private val canGoBackCallbackInner = object : CanGoBack {
        override fun canGoBack() = canGoBack
    }

    init {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        nextPageCommunication.map(firstPagerData)
        listMutator.setTransformable(communication)
    }

    override fun updateCallbacks() =
        canGoBackCallback.updateCallback(canGoBackCallbackInner)

    fun observeNextPageCommunication(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<PagerData>
    ) {
        nextPageCommunication.observe(lifecycleOwner, observer)
    }

    fun observeRetryCommunication(lifecycleOwner: LifecycleOwner,
                                  observer: Observer<PagerData>)
    {
        retryCommunication.observe(lifecycleOwner,observer)
    }

    fun getInfoNextPage(pageNumber: PagerData) {
        canGoBack = false
        progressCommunication.map(Visibility.Visible())
        handle {
          interactor.getListOfPlanetsByPage(pageNumber, atFinish = atFinish, successful = {planetsUi ->
            communication.map(planetsUi)
          })
        }
    }

    override fun retry(pagerData: PagerData) {
        getInfoNextPage(pagerData)
    }

    fun observeListMutator(owner: LifecycleOwner, observer: Observer<PlanetsUi>) {
        this.listMutator.observe(owner, observer)
    }
}