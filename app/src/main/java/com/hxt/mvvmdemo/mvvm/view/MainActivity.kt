package com.hxt.mvvmdemo.mvvm.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hxt.mvvmdemo.R
import com.hxt.mvvmdemo.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_test.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.user.observe(this, Observer {
            tvName.text = it?.name
            tvAge.text = it?.age
        })
        Log.d("xiaoting", "viewmodel$mainViewModel")

        initListener()
    }

    private fun initListener() {
        btnTest.setOnClickListener {
            mainViewModel.getUserData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("xiaoting", "onDestroy")
    }
}
