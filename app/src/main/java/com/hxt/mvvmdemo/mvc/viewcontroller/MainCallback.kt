package com.hxt.mvvmdemo.mvc.viewcontroller

import com.hxt.mvvmdemo.mvc.model.entity.User

interface MainCallback: BaseCallback {
    fun updateView(user: User)
}