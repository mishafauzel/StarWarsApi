package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.presentation.planets.base_communications.RetryCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class SomethingWentWrongItem(
    private val retry: RetryCommunication.Update,
    private val message:String,
    private val pagerData: PagerData

    ) : ItemUi {

    override fun type()=5


    override fun show(vararg views: MyView) {
        views[0].handleClick {
            retry.map(pagerData)
        }
        views[1].show(message)
    }

    override fun id() = "-5"


    override fun content() = "-24"

}