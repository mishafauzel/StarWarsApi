package com.example.starwarsapi.presentation.planets

import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.R
import com.example.starwarsapi.presentation.main.MainActivity
import com.example.starwarsapi.presentation.planets.adapter.PlanetsAdapter
import com.github.johnnysc.coremvvm.presentation.BackPress

class PlanetsFragment : BackPress.Fragment<PlanetsUi, PlanetsViewModel>() {
    override fun viewModelClass() = PlanetsViewModel::class.java

    override val layoutResId = R.layout.single_recycler_view_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val planetsAdapter = PlanetsAdapter.Planets()
        recyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = planetsAdapter



        viewModel.observe(this) { planetsUI ->
            planetsUI.map(planetsAdapter)

        }
        viewModel.observeException(this){
            viewModel.addSomethingWentWrong()
        }




    }
}