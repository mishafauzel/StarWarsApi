package com.example.starwarsapi.presentation.planets.base_communications

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.presentation.LiveDataTransformator
import com.example.starwarsapi.presentation.planets.items.CharacterItem
import com.example.starwarsapi.presentation.planets.items.PlanetItem
import com.example.starwarsapi.presentation.planets.items.SomethingWentWrongItem
import com.example.starwarsapi.presentation.planets.basedata.PlanetsUi
import com.example.starwarsapi.presentation.planets.items.PagerItem
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import kotlin.math.log


class ListMutator(

    private val mapperPlanUiToList: PlanetsUi.Mapper<List<ItemUi>>,

    ) : LiveDataTransformator<PlanetsUi, PlanetsUi>() {
    private val totalList: MutableList<ItemUi> = mutableListOf()
    private val listOfClosedPlanetsID: MutableList<Int> = mutableListOf()

    override fun transform(from: PlanetsUi): PlanetsUi {
        val listFromPlanetsUi = from.map(mapperPlanUiToList)
        filterSomeWentWrongAndPagersItems(totalList)
        totalList.addAll(listFromPlanetsUi)
        val result = filterTotalList()
        return PlanetsUi.Base(result)
    }

    fun changeIdList(id: Int) {
        if (listOfClosedPlanetsID.contains(id)) {
            listOfClosedPlanetsID.remove(id)
        } else {
            listOfClosedPlanetsID.add(id)
        }
        val result = filterTotalList()
        outputLiveData.value = PlanetsUi.Base(result)
    }

    fun filterTotalList(): List<ItemUi> {
        val result = mutableListOf<ItemUi>()
        var isClosed = false
        for (item in totalList) {
            if (item is PlanetItem) {
                isClosed = item.checkIsExistsInList(listOfClosedPlanetsID)
                result.add(item)
            }
            if (item is CharacterItem) {
                if (!isClosed)
                    result.add(item)
            }
            if (item is SomethingWentWrongItem) {
                result.add(item)
            }
            if (item is PagerItem) {
                result.add(item)
            }

        }
        return result.toList()
    }

    fun filterSomeWentWrongAndPagersItems(list: MutableList<ItemUi>): MutableList<ItemUi> {
        val deletingList = list.filter { itemUi ->
            itemUi is SomethingWentWrongItem || itemUi is PagerItem

        }
        list.removeAll(deletingList)
        return list
    }

    fun observe(owner: LifecycleOwner, observer: Observer<PlanetsUi>) {
        observeOutput(owner, observer)
    }


}





