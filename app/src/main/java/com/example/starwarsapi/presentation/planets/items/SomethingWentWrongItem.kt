package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.core.Retry
import com.example.starwarsapi.presentation.planets.base_communications.NextPageCommunication
import com.example.starwarsapi.presentation.planets.basedata.PagerData
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class SomethingWentWrongItem(
    private val retry: Retry,

) : ItemUi {
    override fun type(): Int {
        return 5
    }

    override fun show(vararg views: MyView) {
        views[0].handleClick {
            retry.retry()
        }
    }

    override fun id(): String {
        return "-5"
    }

    override fun content(): String {
        return "-24"
    }
}