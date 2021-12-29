package com.hxt.mvvmdemo.anr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hxt.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_anr_test.*

class ANRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anr_test)
        initListener()
    }

    fun initListener() {
        btnTest.setOnClickListener {
            Thread.sleep(10000)
        }
        btnFinish.setOnClickListener {
            finish()
        }
    }
}