package com.example.starwarsapi.domain.planets.smartcontract

import com.example.starwarsapi.domain.planets.CharacterDomain
import com.example.starwarsapi.domain.planets.PlanetDomain
import com.example.starwarsapi.presentation.planets.items.CharacterItem
import com.example.starwarsapi.presentation.planets.base_communications.ListMutator
import com.example.starwarsapi.presentation.planets.items.PlanetItem
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

open class PlanetDomainToItemUI(
    private val charMapper: CharacterDomain.Mapper<CharacterItem>,
    private val listMutator: ListMutator
) : PlanetDomain.Mapper<List<ItemUi>> {
    override fun map(id: Int, name: String, listOfResidence: List<CharacterDomain>): List<ItemUi> {
        val mutableList = mutableListOf<ItemUi>()
        mutableList.add(PlanetItem(id, name, listMutator))
        val listOfCharUI = listOfResidence.map {
            it.map(charMapper)
        }
        mutableList.addAll(listOfCharUI)
        return mutableList.toList()
    }

    override suspend fun map(id: Int, name: String, listOfResidence: List<String>): List<ItemUi> {
        throw IllegalStateException()
    }
}