package com.hxt.mvvmdemo.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hxt.mvvmdemo.mvc.model.entity.User
import com.hxt.mvvmdemo.mvc.model.MVCModel
import com.hxt.mvvmdemo.mvvm.model.MVVMModel

class MainViewModel : ViewModel() {
    var user: MutableLiveData<User> = MutableLiveData<User>()

    fun getUserData() {
        user.value = MVVMModel.getUserDataFromNative()
    }

    override fun onCleared() {
        super.onCleared()
    }
}