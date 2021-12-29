package com.hxt.mvvmdemo.mvp.view

import android.os.Bundle
import com.hxt.mvvmdemo.R
import com.hxt.mvvmdemo.mvp.model.entity.User
import com.hxt.mvvmdemo.mvp.presenter.BasePresenter
import com.hxt.mvvmdemo.mvp.presenter.UserPresenter
import kotlinx.android.synthetic.main.activity_test.*

class MVPActivity : BaseActivity(), UserView {
    override fun getContentView(): Int = R.layout.activity_test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    override fun initPresenter(): BasePresenter {
        return UserPresenter(this)
    }

    private fun initListener() {
        btnTest.setOnClickListener {
            (presenter as? UserPresenter)?.getUserData()
        }
    }

    override fun updateUserInfo(user: User) {
        tvName.text = user.name
        tvAge.text = user.age
    }
}