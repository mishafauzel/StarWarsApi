package com.example.starwarsapi.presentation.planets

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.starwarsapi.presentation.LiveDataTransformator
import com.example.starwarsapi.presentation.Transformable
import com.example.starwarsapi.presentation.planets.adapter.PlanetsAdapter
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.Communication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi




    class ListMutator(

        val mapper: PlanetsUi.Mapper<List<ItemUi>>
    ) : LiveDataTransformator<PlanetsUi, PlanetsUi>()  {
        private val totalList: MutableList<ItemUi> = mutableListOf()
        private val listOfClosedPlanetsID: MutableList<Int> = mutableListOf()

        override fun transform(from: PlanetsUi): PlanetsUi {
            totalList.clear()
            totalList.addAll(from.map(mapper))
            val result=filterTotalList()

            return PlanetsUi.Base(planetUI = result)
        }

        fun changeIdList(id: Int) {
            if (listOfClosedPlanetsID.contains(id)) {
                listOfClosedPlanetsID.remove(id)
            }
            else
            {
                listOfClosedPlanetsID.add(id)
            }
            val result=filterTotalList()
            outputLiveData.value= PlanetsUi.Base(planetUI = result)
        }

         fun filterTotalList(): List<ItemUi> {
            val result = mutableListOf<ItemUi>()
            var isClosed: Boolean = false
            for (item in totalList) {
                if (item is PlanetItem) {
                    isClosed = item.checkIsExistsInList(listOfClosedPlanetsID)
                    result.add(item)
                }
                if (item is CharacterItem) {
                    if (!isClosed)
                        result.add(item)
                }
                if(item is SomethingWentWrongItem)
                {
                    result.add(item)
                }

            }
            return result.toList()
        }

         fun observe(owner: LifecycleOwner, observer: Observer<PlanetsUi>) {
            observeOutput(owner,observer)
        }


    }





