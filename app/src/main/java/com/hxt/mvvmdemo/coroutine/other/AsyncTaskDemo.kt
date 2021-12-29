package com.hxt.mvvmdemo.coroutine.other

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import com.hxt.mvvmdemo.retrofit2.Repo
import com.hxt.mvvmdemo.retrofit2.Request

class AsyncTaskDemo : AsyncTask<Void, Void, Repo?>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): Repo? {
        // get repo in work thread
        return null
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Repo?) {
        super.onPostExecute(result)
        // update view in main thread
        // updateView(result)
    }

//    private fun getUserInfo(): String {
//        Thread.sleep(5000)
//        return "zhangsan"
//    }

    private val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // update view in main thread
//            updateView()
        }
    }
}