package com.hxt.mvvmdemo.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hxt.mvvmdemo.R
import com.hxt.mvvmdemo.retrofit2.Request
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor

class CoroutineActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "xiaoting"
        private const val NAME = "CoroutineActivity"
    }

    private val customCoroutine =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineName(NAME))
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        testGlobalScope()
//        testMainCoroutine()
//        testCustomCoroutine()
    }

    private suspend fun switchThread() {
        withContext(Dispatchers.IO) {
            // do time-consuming task
        }
        withContext(Dispatchers.Main) {
            // do things in main thread
        }
        withContext(Dispatchers.IO) {

        }
        withContext(Dispatchers.Main) {

        }
    }

    private fun testGlobalScope() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "GlobalScope task run on thread -- " + Thread.currentThread().name)
        }
    }

    private fun testCustomCoroutine() {
        customCoroutine.launch {
            Log.d(
                TAG,
                "custom coroutine " + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
            withContext(Dispatchers.IO) {
                delay(5000)
                Log.d(TAG, "customCoroutine task run on thread -- " + Thread.currentThread().name)
            }
            Log.d(TAG, "testCustomCoroutine -- " + Thread.currentThread().name)
        }
    }

    private fun testMainCoroutine() {
        mainScope.launch {
            Log.d(
                TAG,
                "main scope" + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
//            withContext()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}