package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject

class Engine @Inject constructor() {
    var info = ""
    fun getEngineInfo(): String {
        return "$info"
    }
}