package com.hxt.mvvmdemo.toothpick.tpmodule

import com.hxt.mvvmdemo.toothpick.`interface`.Car
import com.hxt.mvvmdemo.toothpick.model.Audi
import toothpick.config.Module

class MainModule : Module() {
    init {
        bind(Car::class.java).to(Audi::class.java)
    }
}