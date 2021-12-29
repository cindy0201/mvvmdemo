package com.hxt.mvvmdemo

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * get context which can be used for getting system resources
 */
fun getContext(): Context {
    return InstrumentationRegistry.getInstrumentation().context
}

/**
 * get json file from asset and translate it to string
 */
private fun getJsonString(): String {
    val stringBuilder = StringBuilder()
    val assetManager = getContext().assets
    try {
        val bufferedReader = BufferedReader(
            InputStreamReader(
                assetManager.open("test_data_config.json"), "utf-8"
            )
        )
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stringBuilder.toString()
}

/**
 * translate test data from json to class
 */
fun translateTestData(): TestData {
    val jsonStr = getJsonString()
    Log.d("xiaoting", "jsonStr is $jsonStr")
    return Gson().fromJson(jsonStr, TestData::class.java)
}

/**
 * get single TestData instance
 */
fun getTestData(): TestData {
    return TestData.getInstance()
}