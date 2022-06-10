package com.example.starwarsapi.presentation.characters

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class CharacterUI(
    private val id:Int,
    private val name:String,
    private val height:Int,
    private val mass:Int,
    private val birthYear:String,
    private val gender:String
    ):ItemUi {
    override fun type(): Int {
        return 2//не хорошо, лучше вынести в отдельный sealed class
    }

    override fun show(vararg views: MyView) {

    }

    override fun id(): String {
        return id.toString()
    }

    override fun content(): String {
        return "$id, $name, $height,$mass,$birthYear,$gender"
    }
}