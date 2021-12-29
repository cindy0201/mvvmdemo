package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Train @Inject constructor() {
    fun getInfo(): String {
        return "train"
    }
}