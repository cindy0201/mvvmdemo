package com.hxt.mvvmdemo.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofitClient by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun getApiInterface(): ApiInterface {
        return retrofitClient.build().create(ApiInterface::class.java)
    }
}