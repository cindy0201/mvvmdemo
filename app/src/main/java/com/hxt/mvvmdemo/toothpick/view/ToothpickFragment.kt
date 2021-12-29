package com.hxt.mvvmdemo.toothpick.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hxt.mvvmdemo.R
import com.hxt.mvvmdemo.toothpick.`interface`.Car
import com.hxt.mvvmdemo.toothpick.tpmodule.MainModule
import kotlinx.android.synthetic.main.fragment_toothpick.*
import toothpick.Toothpick
import javax.inject.Inject

class ToothpickFragment : Fragment() {
//    @Inject
//    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var car: Car

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toothpick, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToothpick()
        initListener()
    }

    private fun initToothpick() {
        val scope = Toothpick.openScopes(activity?.application, this)
        scope.installModules(MainModule())
        Toothpick.inject(this, scope)
    }

    private fun initListener() {
        btnTest.setOnClickListener {
            tvResult.text = car.getName()
        }
    }
}