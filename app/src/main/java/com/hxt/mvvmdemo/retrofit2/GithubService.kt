package com.hxt.mvvmdemo.retrofit2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo>>

    @GET("users/{user}/repos")
    suspend fun listRepoKt(@Path("user") user: String?): List<Repo>
}