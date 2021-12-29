package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Ship @Inject constructor() {
    fun getInfo(): String {
        return "ship"
    }
}