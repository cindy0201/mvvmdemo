package com.hxt.mvvmdemo.mvc.model

open class BaseModel {
    open fun onDestroy() {
        // close dataBase
        // remove callback
    }
}