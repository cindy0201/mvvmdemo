package com.hxt.mvvmdemo.mvp.view

import com.hxt.mvvmdemo.mvp.model.entity.User

interface UserView : BaseView {
    fun updateUserInfo(user: User)
}