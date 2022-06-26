package com.example.starwarsapi.presentation.planets.items

import com.example.starwarsapi.presentation.character.nav_screen.CharacterFullInfoScreen
import com.example.starwarsapi.sl.main.DataQueue
import com.github.johnnysc.coremvvm.presentation.NavigationCommunication
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class CharacterItem(
    private val id: Int,
    private val planetId: Int,
    private val characterName: String,
    private val birthYear: String,
    private val navigationCommunication: NavigationCommunication.Mutable,
    private val dataQueue: DataQueue.Update<Any>
) : ItemUi {

    override fun type() =
        if (id == -1) 3
        else 2


    override fun show(vararg views: MyView) {
        if (id != -1) {
            views[0].show(characterName)
            views[1].show(birthYear)
            views[2].handleClick {
                navigationCommunication.map(CharacterFullInfoScreen())
                dataQueue.update(id)
            }
        }
    }

    override fun id() = id.toString()


    override fun content() = "$id,$characterName,$birthYear"

}


