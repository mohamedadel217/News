package com.example.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatcherProvider : DispatcherProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun mainThread(): CoroutineDispatcher = Dispatchers.Main
    override fun default(): CoroutineDispatcher = Dispatchers.Default
}