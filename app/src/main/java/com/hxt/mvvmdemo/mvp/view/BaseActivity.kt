package com.hxt.mvvmdemo.mvp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hxt.mvvmdemo.mvp.presenter.BasePresenter

abstract class BaseActivity : AppCompatActivity() {
    var presenter: BasePresenter? = null

    open fun getContentView() = 0
    abstract fun initPresenter(): BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initPresenter()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        presenter?.onLowMemory()
    }
}