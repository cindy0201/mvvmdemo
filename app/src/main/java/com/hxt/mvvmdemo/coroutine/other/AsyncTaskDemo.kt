package com.hxt.mvvmdemo.coroutine.other

import android.os.AsyncTask
import com.hxt.mvvmdemo.retrofit2.Repo

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
}