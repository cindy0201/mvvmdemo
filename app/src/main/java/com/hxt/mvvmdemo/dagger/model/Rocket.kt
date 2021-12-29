package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Rocket @Inject constructor() {
    fun getInfo(): String {
        return "rocket"
    }
}