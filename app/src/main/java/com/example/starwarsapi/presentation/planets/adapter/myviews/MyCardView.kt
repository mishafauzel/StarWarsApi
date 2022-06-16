package com.example.starwarsapi.presentation.planets.adapter.myviews

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class MyCardView : CardView, MyView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun handleClick(listener: OnClickListener) {
        setOnClickListener(listener)
    }
}