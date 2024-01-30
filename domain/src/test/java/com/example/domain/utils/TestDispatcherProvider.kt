package com.example.domain.utils

import com.example.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider: DispatcherProvider {

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun mainThread(): CoroutineDispatcher = Dispatchers.Main

    override fun default(): CoroutineDispatcher = Dispatchers.Default

}