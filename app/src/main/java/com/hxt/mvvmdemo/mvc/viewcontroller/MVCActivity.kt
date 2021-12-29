package com.hxt.mvvmdemo.mvc.viewcontroller

import android.os.Bundle
import com.hxt.mvvmdemo.R
import com.hxt.mvvmdemo.mvc.model.BaseModel
import com.hxt.mvvmdemo.mvc.model.MVCModel
import com.hxt.mvvmdemo.mvc.model.entity.User
import kotlinx.android.synthetic.main.activity_test.*

class MVCActivity : BaseActivity() {

    override fun getContentView(): Int = R.layout.activity_test

    override fun getMyModel(): BaseModel {
        return MVCModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        btnTest.setOnClickListener {
            (model as? MVCModel)?.getUserData(object : MainCallback {
                override fun updateView(user: User) {
                    tvName.text = user.name
                    tvAge.text = user.age
                }
            })
        }
    }
}