package com.hxt.mvvmdemo.retrofit2

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Request {
    fun request(callback: ((result: Repo?) -> Unit)?) {
        val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = mRetrofit.create(GithubService::class.java)
        val call = service.listRepos("octocat")
        call.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                callback?.invoke(response.body()?.get(1))
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.e("xiaoting", t.message ?: "##")
            }
        })
    }

    suspend fun getRepo(): Repo {
        val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = mRetrofit.create(GithubService::class.java)
        val list = service.listRepoKt("octocat")
        return list[1]
    }


}