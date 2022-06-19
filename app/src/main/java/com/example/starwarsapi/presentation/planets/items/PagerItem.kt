package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface PagerItem : ItemUi {

    class Base(
        private val pagerData: PagerData,
        private val nextPageCommunication: NextPageCommunication.Update
    ) :
        PagerItem {

        override fun type() = 4

        override fun show(vararg views: MyView) {
            views[0].handleClick() {
                nextPageCommunication.map(pagerData)
            }
        }

        override fun id() = "${pagerData.hashCode()}"

        override fun content() = "$pagerData"

    }


    class ThereAreNoMoreResults : PagerItem {

        override fun type() = 6


        override fun show(vararg views: MyView) {}

        override fun id() = "therearenomoreresults"


        override fun content() = "therearenomoreresults"


    }
}



