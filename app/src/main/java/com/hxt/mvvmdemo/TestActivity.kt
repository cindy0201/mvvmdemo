package com.hxt.mvvmdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hxt.mvvmdemo.anr.ANRActivity
import com.hxt.mvvmdemo.coroutine.CoroutineActivity
import com.hxt.mvvmdemo.dagger.view.DaggerActivity
import com.hxt.mvvmdemo.mvc.viewcontroller.MVCActivity
import com.hxt.mvvmdemo.mvp.view.MVPActivity
import com.hxt.mvvmdemo.mvvm.view.MainActivity
import com.hxt.mvvmdemo.notification.MyNotificationManager
import com.hxt.mvvmdemo.toothpick.view.ToothpickActivity
import kotlinx.android.synthetic.main.activity_main_test.*
import kotlinx.coroutines.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)
        btnTestMVC.text = getFormatString(R.string.test)

        initListener()
    }

    private fun getFormatString(resId: Int, vararg formatArgs: Any?): String {
        return getString(resId, *formatArgs)
    }

    private fun initListener() {
        btnTest.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnTestMVC.setOnClickListener {
            startActivity(Intent(this, MVCActivity::class.java))
        }

        btnTestMVP.setOnClickListener {
            startActivity(Intent(this, MVPActivity::class.java))
        }

        btnJumpDaggerActivity.setOnClickListener {
            startActivity(Intent(this, DaggerActivity::class.java))
        }

        btnJumpTPActivity.setOnClickListener {
            startActivity(Intent(this, ToothpickActivity::class.java))
        }

        btnNotification.setOnClickListener {
            MyNotificationManager.startNotification(this)
        }

        btnNotification2.setOnClickListener {
            MyNotificationManager.startNotification2(this)
        }

        anr.setOnClickListener {
            startActivity(Intent(this, ANRActivity::class.java))
        }

        btnCoroutineTest.setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }
    }
}