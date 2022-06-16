package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface PagerItem : ItemUi {

    data class Base(
        private val pagerData: PagerData,

        private val nextPageCommunication: NextPageCommunication.Update
    ) :
        PagerItem {
        override fun type(): Int {
            return 4
        }

        override fun show(vararg views: MyView) {
            views[0].handleClick() {
                nextPageCommunication.map(pagerData)
            }
        }

        override fun id(): String {
            return "${pagerData.hashCode()}"
        }

        override fun content(): String {
            return "$pagerData"
        }
    }

    class ThereIsnoMoreResults : PagerItem {
        override fun type(): Int {
            return 6
        }

        override fun show(vararg views: MyView) {

        }

        override fun id(): String {
            return "therearenomoreresults"
        }

        override fun content(): String {
            return "therearenomoreresults"
        }

    }


}
