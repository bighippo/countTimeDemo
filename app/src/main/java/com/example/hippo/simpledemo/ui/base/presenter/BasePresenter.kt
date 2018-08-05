package com.example.hippo.simpledemo.ui.base.presenter

import com.example.hippo.simpledemo.ui.base.view.BaseView

interface BasePresenter {
    //fun onCreate()

    //fun onStop()

    fun attachView(view: BaseView)
}