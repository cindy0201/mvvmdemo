package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Tyre @Inject constructor() {
    fun getInfo(): String {
        return "tyre"
    }
}