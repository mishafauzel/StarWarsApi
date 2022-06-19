package com.example.starwarsapi.presentation.character.items

import com.example.starwarsapi.presentation.character.base_communications.RetryCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class SomethingWentWrong(
    private val retryCommunication: RetryCommunication.Update,
    private val message: String
) : ItemUi {

    override fun content() = "something went wrong"

    override fun id() = "Something went wrong"

    override fun show(vararg views: MyView) {
        views[0].handleClick {
            retryCommunication.map(1)
        }
        views[1].show(message)
    }

    override fun type() = 5

}