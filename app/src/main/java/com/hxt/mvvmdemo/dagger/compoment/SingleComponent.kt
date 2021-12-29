package com.hxt.mvvmdemo.dagger.compoment

import com.hxt.mvvmdemo.dagger.daggermodule.SingleModule
import com.hxt.mvvmdemo.dagger.view.MainFragment
//import dagger.Component
import javax.inject.Singleton


@Singleton
//@Component(modules = [SingleModule::class])
interface SingleComponent {
    fun inject(fragment: MainFragment)
}