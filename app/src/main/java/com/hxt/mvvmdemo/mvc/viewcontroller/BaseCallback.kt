package com.hxt.mvvmdemo.mvc.viewcontroller

interface BaseCallback {
    fun showLoading() = Unit
    fun stopLoading() = Unit
    fun showErrorView() = Unit
    fun showEmptyView() = Unit
}