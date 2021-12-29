package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Moto @Inject constructor() {
    fun getInfo(): String {
        return "moto"
    }
}