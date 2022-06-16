package com.example.starwarsapi.presentation.character.base_communications

import com.example.starwarsapi.presentation.character.CharacterFullUI
import com.github.johnnysc.coremvvm.presentation.Communication

interface CharacterFullComunication : Communication.Mutable<CharacterFullUI> {
    class Base : Communication.UiUpdate<CharacterFullUI>(), CharacterFullComunication

}