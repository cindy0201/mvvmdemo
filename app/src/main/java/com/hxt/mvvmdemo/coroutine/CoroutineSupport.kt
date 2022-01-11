package com.hxt.mvvmdemo.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineSupport(parent: Job? = null) : CoroutineScope {

    private val job: Job = if (parent == null) {
        SupervisorJob()
    } else {
        SupervisorJob(parent)
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("${throwable.message}")
    }

    override val coroutineContext: CoroutineContext
        get() = job + exceptionHandler + Dispatchers.Main

    fun destroy() {
        job.cancel()
    }

    fun cancelChildren() {
        job.cancelChildren()
    }
}