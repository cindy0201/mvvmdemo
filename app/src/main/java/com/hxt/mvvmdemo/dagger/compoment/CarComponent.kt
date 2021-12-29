package com.hxt.mvvmdemo.dagger.compoment

import com.hxt.mvvmdemo.dagger.daggermodule.SingleModule
import com.hxt.mvvmdemo.dagger.view.MainFragment
//import dagger.Component


//@Component(modules = [SingleModule::class])
interface CarComponent {
    fun inject(fragment: MainFragment)
}
