package com.hxt.mvvmdemo.mvvm.model

import com.hxt.mvvmdemo.mvc.model.entity.User
import com.hxt.mvvmdemo.retrofit.RequestCallback
import com.hxt.mvvmdemo.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MVVMModel {
    fun getUserDataFromRemote(callback: RequestCallback?) {
        var user: User? = null
        val call = RetrofitClient.getApiInterface().getUserService()
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    user = response.body()
                    callback?.onSuccess()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback?.onFailed()
            }

        })
    }

    fun getUserDataFromNative(): User {
        return User("tina","18")
    }
}