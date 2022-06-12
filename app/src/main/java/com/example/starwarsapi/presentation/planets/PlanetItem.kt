package com.example.starwarsapi.presentation.planets

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class PlanetItem(private val id:Int, private val name:String):ItemUi {
    override fun type()= 1


    override fun show(vararg views: MyView) {
        views[0].show(name)

    }

    override fun id(): String {
        return id.toString()
    }

    override fun content(): String {
        return "$id $name"
    }


}
