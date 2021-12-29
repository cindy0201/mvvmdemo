package com.hxt.mvvmdemo.dagger.model

import javax.inject.Inject


class Car @Inject constructor(var engine: Engine) {
    @Inject
    lateinit var tyre: Tyre

    var color: String = ""
    fun getCarColorText(): String {
        return "the car's color is $color"
    }

    fun getEngineInfo(): String {
        engine.info = "engine test"
        return engine.getEngineInfo()
    }

    fun getTyreInfo(): String {
        // 这个地方会新建一个tyre对象，与DaggerCarComponent创建的tyre对象不是同一个，所以后续调用的getInfo()
        // 也是新建的对象
//        DaggerTyreComponent.create().inject(this)

        return tyre.getInfo()
    }

}
