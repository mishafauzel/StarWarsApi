package com.example.starwarsapi.presentation.character

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView
import com.google.gson.annotations.SerializedName

data class CharacterFullInfoItem(
    private val name: String,
    private val birthYear: String,
    private val id: Int,
    private val hairColor: String,
    private val skinColor: String,
    private val gender: String,
    private val mass: String,
    private val height: String
) : ItemUi {
    override fun type(): Int {
        return 4
    }

    override fun show(vararg views: MyView) {
        views[0].show(name)
        views[1].show(birthYear)
        views[2].show(hairColor)
        views[3].show(skinColor)
        views[4].show(gender)
        views[5].show(mass)
        views[6].show(height)
    }

    override fun id(): String {
        return id.toString()
    }

    override fun content(): String {
        return "$id,$name,$birthYear,$hairColor,$skinColor,$gender,$mass,$height"
    }
}