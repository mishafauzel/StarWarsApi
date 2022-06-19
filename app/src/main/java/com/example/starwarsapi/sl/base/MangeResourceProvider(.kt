package com.example.starwarsapi.sl.base

import android.content.Context
import com.github.johnnysc.coremvvm.core.ManageResources

interface MangeResourceProvider {

    fun provideManageResource(context:Context):ManageResources

    class Base():MangeResourceProvider
    {
        override fun provideManageResource(context: Context): ManageResources {
            return ManageResources.Base(context)
        }

    }

}