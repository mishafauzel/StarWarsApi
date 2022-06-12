package com.example.starwarsapi.presentation.planets

import com.example.starwarsapi.core.Retry
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class SomethingWentWrongItem(private val retry: Retry):ItemUi {
    override fun type(): Int {
        return 5
    }

    override fun show(vararg views: MyView) {
        views[0].handleClick{
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