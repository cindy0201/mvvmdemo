package com.hxt.mvvmdemo.mvp.view

interface BaseView {
    fun showLoading() = Unit
    fun stopLoading() = Unit
    fun showErrorView() = Unit
    fun showEmptyView() = Unit
}