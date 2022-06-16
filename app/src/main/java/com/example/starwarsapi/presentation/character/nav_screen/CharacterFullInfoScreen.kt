package com.example.starwarsapi.presentation.character.nav_screen


import com.example.starwarsapi.presentation.character.CharacterFullFragment
import com.github.johnnysc.coremvvm.presentation.NavigationScreen
import com.github.johnnysc.coremvvm.presentation.ShowStrategy

class CharacterFullInfoScreen(showStrategy: ShowStrategy = ShowStrategy.REPLACE) :
    NavigationScreen("CharacterFullInfoStrategy", CharacterFullFragment::class.java, showStrategy)