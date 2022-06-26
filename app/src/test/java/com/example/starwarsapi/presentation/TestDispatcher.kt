package com.example.starwarsapi.presentation

import com.github.johnnysc.coremvvm.core.Dispatchers
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class TestDispatcher(private val context: CoroutineContext):Dispatchers {

    override suspend fun changeToUI(block: suspend CoroutineScope.() -> Unit) {
        withContext(context,block)
    }



    override fun launchBackground(
        scope: CoroutineScope,
        block: suspend CoroutineScope.() -> Unit
    ): Job {

        val job=scope.launch(context,block=block)

        return job
    }

    override fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job {

        val job=scope.launch(context,block=block)
        return job

    }
}