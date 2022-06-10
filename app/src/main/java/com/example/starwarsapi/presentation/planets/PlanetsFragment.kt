package com.example.starwarsapi.presentation.planets

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.github.johnnysc.coremvvm.presentation.BackPress

class PlanetsFragment : BackPress.Fragment<PlanetsUi, PlanetsViewModel>() {
    override fun viewModelClass() = PlanetsViewModel::class.java
    override val layoutResId = R.layout.single_recycler_view_layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView=view.findViewById<RecyclerView>(R.id.recycler_view)
        viewModel.observe(this){

        }
    }
}