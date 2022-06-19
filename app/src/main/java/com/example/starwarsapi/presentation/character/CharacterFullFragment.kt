package com.example.starwarsapi.presentation.character

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.character.adapter.CharacterAdapter
import com.example.starwarsapi.presentation.character.base_data.CharacterFullUI
import com.github.johnnysc.coremvvm.presentation.BackPress

class CharacterFullFragment : BackPress.Fragment<CharacterFullUI, CharacterFullViewModel>() {

    override fun viewModelClass() = CharacterFullViewModel::class.java

    override val layoutResId = R.layout.single_recycler_view_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = CharacterAdapter.Base()
        recyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        viewModel.observe(this) { characterFullUI ->
            characterFullUI.map(adapter)
        }

        viewModel.observeRetryCommunication(this) {
            viewModel.retry()
        }


    }


}