package com.hxt.mvvmdemo.dagger.compoment

import com.hxt.mvvmdemo.dagger.model.Car
//import dagger.Component

//@Component
interface TyreComponent {
    fun inject(car: Car)
}