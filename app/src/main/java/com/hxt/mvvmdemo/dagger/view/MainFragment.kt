package com.hxt.mvvmdemo.dagger.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hxt.mvvmdemo.R
//import com.hxt.mvvmdemo.dagger.compoment.DaggerCarComponent
import com.hxt.mvvmdemo.dagger.model.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var car: Car

    @Inject
    lateinit var flight: Flight

    @Inject
    lateinit var moto: Moto

    @Inject
    lateinit var rocket: Rocket

    @Inject
    lateinit var ship: Ship

    @Inject
    lateinit var train: Train

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // create car instance
//        DaggerCarComponent.create().inject(this)

        initListener()
    }

    private fun initListener() {
        btnTest.setOnClickListener {
            car.color = "red"
            tvResult.text = StringBuilder().apply {
                append(car.getCarColorText())
                append(flight.getInfo())
                append(moto.getInfo())
                append(rocket.getInfo())
                append(ship.getInfo())
                append(train.getInfo())
            }.toString()
            tvCar.text = car.getTyreInfo()
        }

        btnSingle.setOnClickListener {

        }
    }
}