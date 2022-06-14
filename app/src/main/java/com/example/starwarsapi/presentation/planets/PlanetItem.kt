package com.example.starwarsapi.presentation.planets

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView
import kotlin.time.measureTime

class PlanetItem(private val id:Int, private val name:String,private val listMutator: ListMutator):ItemUi {
    override fun type()= 1


    override fun show(vararg views: MyView) {
        views[0].show(name)
        views[1].handleClick{
            listMutator.changeIdList(id)
        }

    }

    override fun id(): String {
        return id.toString()
    }

    override fun content(): String {
        return "$id $name"
    }

    fun checkIsExistsInList(list: List<Int>):Boolean
    {
        return list.contains(element = id)
    }


}
