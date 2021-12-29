package com.hxt.mvvmdemo.mvp.model

import com.hxt.mvvmdemo.mvp.model.entity.User

object MVPModel {
    fun getUserDataFromNative(): User {
        return User("Aiden", "60")
    }

    suspend fun getUserDataFromServer(): User {
        // get data from server
        return User("Aiden", "60")
    }

}