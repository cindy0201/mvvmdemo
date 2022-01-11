package com.hxt.mvvmdemo.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hxt.mvvmdemo.R
import com.hxt.mvvmdemo.retrofit2.Repo
import com.hxt.mvvmdemo.retrofit2.Request
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import java.lang.RuntimeException
import kotlin.coroutines.ContinuationInterceptor

class CoroutineActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "xiaoting"
        private const val NAME = "CoroutineActivity"
    }

    private val customCoroutine =
        CoroutineScope(Dispatchers.IO + Job() + CoroutineName(NAME))
    private val mainScope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        btnTest.setOnClickListener {
            getRepoFromGitHub()
        }
        testGlobalScope()
        testCustomCoroutine()
        testMainCoroutine()
        testLifecycleCoroutine()
        testBuilder()
        testJobType()
        case1()
        case2()
        testWithContext()
        test()
        testStartMode()
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
        GlobalScope.launch() {
            delay(1000)
            Log.d(TAG, "GlobalScope task run on thread -- " + Thread.currentThread().name)
        }
    }

    private fun testCustomCoroutine() {
        customCoroutine.launch {
            delay(5000)
            Log.d(
                TAG,
                "aftter delay " + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
        }
        customCoroutine.launch {
            delay(1000)
            Log.d(TAG, "custom coroutine 2")
        }
    }

    private fun testMainCoroutine() {
        Log.d(TAG, "start-" + mainScope.coroutineContext[Job])
        mainScope.launch() {
            Log.d(
                TAG,
                "main scope--" + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
        }
        mainScope.launch {
            Log.d(
                TAG,
                "main scope--" + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
        }
    }

    private fun testLifecycleCoroutine() {
        lifecycleScope.launch {
            delay(5000)
            Log.d(
                TAG,
                "lifecycleScope--" + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
        }
    }

    private fun testBuilder() {
//        testLaunch()
        testAsync()
    }

    private fun testJobType() {
//        testJob()
        testSupervisorJob()
    }

    private fun testSupervisorJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d(TAG, "${throwable.message}")
        }
        val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.IO)
        val job1 = scope.launch() {
            delay(3000)
            Log.d(TAG, "child job1")
            Log.d(TAG, "status is {$scope.isActive}")
        }
        val job2 = scope.launch(SupervisorJob()) {
            delay(2000)
            Log.d(TAG, "child job2")
            throw RuntimeException("something wrong")
        }
//        val job2 = scope.launch {
//            launch {
//                launch {
//                    delay(3000)
//                    Log.d(TAG, "child job5")
//                }
//                delay(2000)
//                Log.d(TAG, "child job3")
//                throw RuntimeException("something wrong")
//            }
//            delay(3000)
//            Log.d(TAG, "child job2")
//        }
        val job3 = scope.launch {
            delay(4000)
            Log.d(TAG, "child job3")
        }
    }

    private fun testJob() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d(TAG, "${throwable.message}")
        }
        val scope = CoroutineScope(exceptionHandler + Dispatchers.IO + Job())
        val job1 = scope.launch() {
            delay(1000)
            Log.d(TAG, "child job1")
        }
        val job2 = scope.launch {
            delay(2000)
            Log.d(TAG, "child job2")
            throw RuntimeException("something wrong")
        }
        val job3 = scope.launch {
            delay(3000)
            Log.d(TAG, "child job3")
        }
    }

    private fun testLaunch() {
        mainScope.launch {
            val repo = getRepoByHttp()
            tvName.text = repo.name
            val repo2 = getRepoByHttp()
            tvAge.text = repo2.id.toString()
        }
    }

    private fun testAsync() {
        val job = mainScope.launch {
            // df1 & df2 run concurrently
            val df1 = async {
                fun1()
            }
            val df2 = async {
                fun2()
            }
            Log.d(TAG, "end")
            // await function need to be called inside coroutine or suspend function
            df1.await()
            df2.await()
        }
    }

    private suspend fun fun1() {
        delay(3000)
        Log.d(TAG, "func 1")
    }

    private suspend fun fun2() {
        delay(1000)
        Log.d(TAG, "func 2")
    }

    private fun testLaunchContext() {
        mainScope.launch(Dispatchers.IO + CoroutineName("TestCoroutineActivity")) {
            delay(1000)
            Log.d(
                TAG,
                "testLaunchContext--" + coroutineContext[Job] + " " + coroutineContext[CoroutineName] + " " + coroutineContext[ContinuationInterceptor]
            )
        }
    }

    private fun case1() {
        val scope = MainScope()
        scope.launch(Job()) {
            launch {
                delay(2000L)
                println("case1-CancelJobActivity job1 finished")
                scope.cancel()
            }
            launch {
                delay(3000L)
                println("case1-CancelJobActivity job2 finished")
            }
        }
    }

    private fun case2() {
        val scope = MainScope()
        scope.launch {
            launch {
                delay(2000L)
                println("case2-CancelJobActivity job1 finished")
                scope.cancel()

            }
            launch {
                delay(3000L)
                println("case2-CancelJobActivity job2 finished")
            }
        }
    }

    private fun testWithContext() {
        mainScope.launch {
            Log.d(TAG, "testWithContext before -- " + coroutineContext[ContinuationInterceptor])
            withContext(Dispatchers.IO) {
                Log.d(TAG, "testWithContext -- " + coroutineContext[ContinuationInterceptor])
            }
            Log.d(TAG, "testWithContext after -- " + coroutineContext[ContinuationInterceptor])
        }
    }

    private fun test() {
        mainScope.launch {
            withContext(Dispatchers.Main) {
                delay(2000)
                Log.d(TAG, "launch 1")
            }
            launch {
                delay(1000)
                Log.d(TAG, "launch 2")
            }
            delay(500)
            Log.d(TAG, "launch end")
        }
    }

    private fun testStartMode() {
//        val jobDefault = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
//            Log.d(TAG, "default-before delay " + Thread.currentThread().name)
//            delay(1000)
//            Log.d(TAG, "default-after delay " + Thread.currentThread().name)
//        }
//        jobDefault.cancel()

        val jobAutomatic = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            Log.d(TAG, "automatic-before delay " + Thread.currentThread().name)
            delay(1000)
            Log.d(TAG, "automatic-after delay " + Thread.currentThread().name)
        }
        jobAutomatic.cancel()

        GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            Log.d(TAG, "before suspend " + Thread.currentThread().name)
            delay(1000)
            Log.d(TAG, "after suspend " + Thread.currentThread().name)
        }
    }


    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
        customCoroutine.cancel()
        mainScope.cancel()
    }

    private fun getRepoFromGitHub() {
        lifecycleScope.launch() {
            val repo = getRepoByHttp()
            tvName.text = repo.name
        }

    }

    private suspend fun getRepoByHttp(): Repo {
        return withContext(Dispatchers.IO) {
            Request().getRepo()
        }
    }

}