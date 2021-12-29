package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Flight @Inject constructor() {
    fun getInfo(): String {
        return "flight"
    }
}