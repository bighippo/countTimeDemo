package com.example.hippo.simpledemo.ui.countdown.view

import com.example.hippo.simpledemo.ui.base.view.MvpView
import com.example.hippo.simpledemo.ui.countdown.model.RecordModel

interface CountDownView : MvpView{
    //fun countdownComplete()

    //fun countdownNext(time:Int)

    fun start()

    fun increase()

    fun decrease()

    fun pause()

    fun reStart()

    fun finishCountDown()
}