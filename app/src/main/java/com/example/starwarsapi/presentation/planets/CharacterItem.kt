package com.example.starwarsapi.presentation.planets

import com.example.starwarsapi.presentation.DataKeeper
import com.example.starwarsapi.presentation.character.CharacterFullInfoScreen
import com.example.starwarsapi.presentation.character.MyShowStrategyp
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class CharacterItem(
    private val id: Int,
    private val characterName: String,
    private val birthYear: String, private val navigationCommunication: NavigationCommunication.Update,
    private val dataKeeper: DataKeeper.Write<Int>
) : ItemUi {
    override fun type(): Int {
        if (id == -1)
            return 3
        return 2
    }

    override fun show(vararg views: MyView) {
        views[0].show(characterName)
        views[1].show(birthYear)
        views[2].handleClick {
            navigationCommunication.map(2)
            dataKeeper.write(id)
        }
    }

    override fun id(): String {
        return id.toString()
    }

    override fun content(): String {
        return "$id,$characterName,$birthYear"
    }
}


