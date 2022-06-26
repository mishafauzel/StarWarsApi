package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.presentation.GetInfoCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface PagerItem : ItemUi {

    class Base(
        private val getInfoCommunication: GetInfoCommunication
    ) :
        PagerItem {

        override fun type() = 4

        override fun show(vararg views: MyView) {
            views[0].handleClick() {
                getInfoCommunication.getNextPage()
            }
        }

        override fun id() = "-1"

        override fun content() = id()

    }


    class ThereAreNoMoreResults : PagerItem {

        override fun type() = 6


        override fun show(vararg views: MyView) {}

        override fun id() = "therearenomoreresults"


        override fun content() = id()


    }
}



