package com.hxt.mvvmdemo.mvc.model

import com.hxt.mvvmdemo.mvc.model.entity.User
import com.hxt.mvvmdemo.mvc.viewcontroller.MainCallback

class MVCModel() : BaseModel() {

    fun getUserData(callback: MainCallback?) {
        val user = getUserDataFromNative()
        callback?.updateView(user)
    }

    private fun getUserDataFromNative(): User {
        return User("tina", "18")
    }
}