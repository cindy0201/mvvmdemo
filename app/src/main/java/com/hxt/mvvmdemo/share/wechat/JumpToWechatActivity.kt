package com.hxt.mvvmdemo.share.wechat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hxt.mvvmdemo.R
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXTextObject
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_test.*


class JumpToWechatActivity : AppCompatActivity() {
    companion object {
        const val APP_ID = ""
        const val REGISTER_NAME = "test"
        const val ACTION_NAME = "wechat test"
    }

    private lateinit var api: IWXAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWeChat()

        setContentView(R.layout.activity_test)

        btnTest.setOnClickListener {
            jumpToWechat()
        }

        tvName.setOnClickListener {
            sendMessageToWechat()
        }
    }

    private val bd = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // 将该app注册到微信
            api.registerApp(REGISTER_NAME)
        }
    }

    private fun initWeChat() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信

        registerReceiver(bd, IntentFilter(ACTION_NAME))
    }

    private fun sendMessageToWechat() {
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        val textObj = WXTextObject()
        textObj.text = "concur"

        val msg = WXMediaMessage()
        msg.mediaObject = textObj
        msg.description = "concur"

        val req = SendMessageToWX.Req()
        req.transaction = System.currentTimeMillis().toString() //transaction字段用与唯一标示一个请求
        req.message = msg

        api.sendReq(req)
    }

    private fun jumpToWechat() {

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(bd)
    }
}