package com.example.starwarsapi.presentation

import androidx.lifecycle.ViewModel
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.coremvvm.presentation.BaseFragment

abstract class FragmentWithExtraInfo<T:ViewModel,extraInfoType>(exttraInfo: extraInfoType):BaseFragment<T>() {


}