package com.hxt.mvvmdemo.dagger.daggermodule

//import dagger.Module
//import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * we can't inject some classes' constructor from third party.
 * so we need to annotation module and provide to inject.
 */
//@Module
class SingleModule {

    @Singleton
//    @Provides
    fun okHttpClientProvider(): OkHttpClient{
        return OkHttpClient()
    }

}