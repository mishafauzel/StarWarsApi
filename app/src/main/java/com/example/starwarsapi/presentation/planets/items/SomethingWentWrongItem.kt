package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.presentation.GetInfoCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class SomethingWentWrongItem(
    private val displayMessage: String,
    private val getInfoCommunication: GetInfoCommunication
    ) : ItemUi,Exception(){

    override fun type() = 5


    override fun show(vararg views: MyView) {
        views[0].handleClick {
            getInfoCommunication.getNextPage()
        }
        views[1].show(displayMessage)
    }

    override fun id() = "-5"


    override fun content() = id()
    override fun equals(other: Any?): Boolean {
        if(other==null)
            return false
        if(other is SomethingWentWrongItem)
        {
            return displayMessage==other.displayMessage
        }
        else return false
    }

}