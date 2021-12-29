package com.hxt.mvvmdemo

import android.content.Context
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.gson.Gson
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ExampleInstrumentedTest {
    companion object {
        private const val TAG = "xiaoting"
//        private lateinit var dic: Dictionary
        private lateinit var appContext: Context
//        lateinit var testData: TestData
        private const val configZh = "{'expense':{'title':'测试MVC'}}"
        private const val configEn = "{'expense':{'title':'Test MVC'}}"

        @BeforeClass
        @JvmStatic
        fun setUp() {
            Log.d(TAG, "setUp")
//            val gson = Gson()
//            dic = if (Locale.getDefault().language == "zh") {
//                gson.fromJson(configZh, Dictionary::class.java)
//            } else {
//                gson.fromJson(configEn, Dictionary::class.java)
//            }
            appContext = InstrumentationRegistry.getInstrumentation().targetContext
//            testData = translateTestData()
        }
    }

    @get:Rule
    var rule = ActivityTestRule(TestActivity::class.java)

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.hxt.mvvmdemo", appContext.packageName)
//    }

    @Test
    fun t1_mvc() {
        Log.d(TAG, "test 1")
        val text = appContext.getString(R.string.test_mvc)
        onView(withId(R.id.btnTestMVC)).check(matches(withText(text)))
        onView(withId(R.id.btnTestMVC)).perform(click())
        Thread.sleep(1 * 1000)
        onView(isRoot()).check(matches(isDisplayed()))
        onView(withId(R.id.btnTest)).perform(click())
        onView(withId(R.id.tvName)).check(matches(withText(getTestData().user?.name)))
        onView(withId(R.id.tvAge)).check(matches(withText(getTestData().user?.age)))
        Thread.sleep(1 * 1000)
//        pressBack()
    }

    @Test
    fun t2_mvp() {
        Log.d(TAG, "test 2")
        onView(withId(R.id.btnTestMVP)).perform(click())
        Thread.sleep(1 * 1000)
        onView(isRoot()).check(matches(isDisplayed()))
        onView(withId(R.id.btnTest)).perform(click())
//        onView(withId(R.id.tvName)).check(matches(withText("tina")))
//        onView(withId(R.id.tvAge)).check(matches(withText("18")))
//        pressBack()
    }

    @After
    fun release() {
        Log.d(TAG, "release")
    }
}