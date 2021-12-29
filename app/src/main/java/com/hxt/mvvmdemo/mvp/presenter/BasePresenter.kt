package com.hxt.mvvmdemo.mvp.presenter

open class BasePresenter {
    open fun onPause() = Unit
    open fun onStop() = Unit
    open fun onDestroy() = Unit
    open fun onLowMemory() = Unit
}