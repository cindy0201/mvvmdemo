package com.hxt.mvvmdemo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.hxt.mvvmdemo.R

object MyNotificationManager {
    private const val CHANNEL_ID = "1"
    private const val CHANNEL_ID_TWO = "2"
    fun startNotification(context: Context) {
//        createNotificationChannel(context)
        val intent = Intent(context, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle("message")
            setContentText("hi, you have an express")
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "message"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = "message notification"
            }
            val channel2 = NotificationChannel(CHANNEL_ID_TWO, name, importance).apply {
                description = "message notification"
            }
            val list = arrayListOf<NotificationChannel>().apply {
                add(channel)
                add(channel2)
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannels(list)
        }
    }

    fun startNotification2(context: Context) {
//        createNotificationChannel(context)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID_TWO).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle("hello")
            setContentText("you need to sleep")
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}