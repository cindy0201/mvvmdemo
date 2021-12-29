package com.hxt.mvvmdemo.mvc.viewcontroller

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hxt.mvvmdemo.mvc.model.BaseModel

abstract class BaseActivity : AppCompatActivity() {
    var model: BaseModel? = null

    abstract fun getMyModel(): BaseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        model = getMyModel()
    }

    open fun getContentView(): Int = 0

    override fun onDestroy() {
        super.onDestroy()
        model?.onDestroy()
    }
}