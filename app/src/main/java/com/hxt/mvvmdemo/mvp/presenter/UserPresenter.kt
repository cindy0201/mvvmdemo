package com.hxt.mvvmdemo.mvp.presenter

import com.hxt.mvvmdemo.mvp.model.MVPModel
import com.hxt.mvvmdemo.mvp.view.UserView

class UserPresenter(view: UserView) : BasePresenter() {
    private var userView: UserView? = view

    fun getUserData() {
        // track event
        val user = MVPModel.getUserDataFromNative()
        userView?.updateUserInfo(user)
    }

    override fun onDestroy() {
        userView = null
    }
}