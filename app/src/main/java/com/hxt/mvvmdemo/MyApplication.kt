package com.hxt.mvvmdemo

import android.app.Application
import toothpick.Toothpick

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Toothpick.openScope("APP")
    }
}