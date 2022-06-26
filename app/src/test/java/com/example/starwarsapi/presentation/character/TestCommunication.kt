package com.example.starwarsapi.presentation.character

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.starwarsapi.presentation.character.base_communications.CharacterFullCommunication
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.github.johnnysc.coremvvm.presentation.Communication
import kotlinx.coroutines.sync.Mutex

class TestCommunication :  CharacterFullCommunication {
    private lateinit var characterFullUI: CharacterFullUI




    fun checkValue(characterFullUI: CharacterFullUI.Base): Boolean {
        println(Thread.currentThread())
        println("something")
        println("live data ${this.characterFullUI}")
        println("expected $characterFullUI")
        return this.characterFullUI == characterFullUI
    }

    override fun map(data: CharacterFullUI) {
        println("data $data")
        characterFullUI = data

    }

    override fun observe(owner: LifecycleOwner, observer: Observer<CharacterFullUI>) {

    }

}