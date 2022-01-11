package com.hxt.mvvmdemo.share.message

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hxt.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_test.*


class JumpToMessageActivity : AppCompatActivity() {
    private val permissionList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btnTest.setOnClickListener {
            jump()
        }
    }

    private fun jumpToContact() {
        val intent = Intent()
        val uri = Uri.parse("content://contacts")
        intent.action = Intent.ACTION_PICK
        intent.data = uri
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            data?.data?.let {
                val result = getContactInfo(it)
                result?.let { phoneNumber ->
                    jumpToSMS(phoneNumber)
//                    sendSms(phoneNumber)
                }
            }
        }
    }

    private fun jumpToSMS(name: String) {
        val smsToUri = Uri.parse("smsto:$name")
        val smsIntent = Intent(Intent.ACTION_VIEW, smsToUri)
        smsIntent.putExtra(
            "sms_body",
            "https://concur/data?page=approve"
        )
        startActivity(smsIntent)
    }

    private fun sendSms(phoneNumber: String) {
        val uri = "concur://test"
        val smsManager = SmsManager.getDefault()
        val smsBody = StringBuffer()
        smsBody.append(Uri.parse(uri))
        smsManager.sendTextMessage("18721977796", null, smsBody.toString(), null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun jump() {
        // 1. request permission at the same time
        // 2. request contact permission, then request send_sms permission
        if (needRequestPermissions()) {
            requestPermissions()
        } else {
            jumpToContact()
        }
    }

    private fun needRequestContactPermission(): Boolean {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && (ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.READ_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED)
    }

    private fun requestContactPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_CONTACTS),
            0
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            var hasGranted = true
            if (grantResults.isNotEmpty()) {
                grantResults.forEach {
                    if (it != PackageManager.PERMISSION_GRANTED) {
                        hasGranted = false
                    }
                }
                if (hasGranted) {
                    jumpToContact()
                }
            }
        }
    }

    private fun getContactInfo(uri: Uri): String? {
        // getPhoneName
//        var result: String? = null
//        val columns = arrayOf(
//            ContactsContract.Contacts.DISPLAY_NAME,
//            ContactsContract.Contacts.HAS_PHONE_NUMBER
//        )
//        val cursor =
//            contentResolver.query(uri, columns, null, null, null)
//        cursor?.let {
//            while (it.moveToNext()) {
//                result = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//                Log.d("xiaoting", "name: $result")
//            }
//        }
//        cursor?.close()
//        return result

        // getPhoneNumber
        val phonesCursor = contentResolver.query(uri, null, null, null, null)

        var phoneNumber: String? = null
        phonesCursor?.let {
            while (it.moveToNext()) {
                phoneNumber =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                Log.d("xiaoting", "$phoneNumber")
            }
        }
        phonesCursor?.close()
        return phoneNumber
    }

    private fun needRequestPermissions(): Boolean {
        permissionList.clear()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    application,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionList.add(Manifest.permission.READ_CONTACTS)
            }
            if (ContextCompat.checkSelfPermission(
                    application,
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionList.add(Manifest.permission.SEND_SMS)
            }
        }
        return permissionList.isNotEmpty()
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            permissionList.toTypedArray(),
            0
        )
    }
}