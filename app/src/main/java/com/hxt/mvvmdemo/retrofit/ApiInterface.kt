package com.hxt.mvvmdemo.retrofit

import com.hxt.mvvmdemo.mvc.model.entity.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET
    fun getUserService(): Call<User>
}