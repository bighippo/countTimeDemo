package com.example.hippo.simpledemo.ui.countdown.presenter


import com.example.hippo.simpledemo.ui.base.presenter.BaseMVPPresenter

import com.example.hippo.simpledemo.ui.countdown.view.CountDownView


class CountDownPresenter : BaseMVPPresenter<CountDownView>() {

    fun startCountDownTimer() {
        if (isViewAttached)
            mMvpView.start()
    }

    fun pause() {
        if (isViewAttached)
            mMvpView.pause()
    }

    fun reStart() {
        if (isViewAttached)
            mMvpView.reStart()
    }

    fun finish() {
        if (isViewAttached)
            mMvpView.finishCountDown()
    }

    fun increase() {
        if (isViewAttached)
            mMvpView.increase()
    }

    fun decrease() {
        if (isViewAttached)
            mMvpView.decrease()
    }
}