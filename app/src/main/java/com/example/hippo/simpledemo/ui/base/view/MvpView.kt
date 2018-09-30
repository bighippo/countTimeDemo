package com.example.hippo.simpledemo.ui.base.view

import android.content.Context

interface MvpView {
    fun showProgress()

    fun closeProgress()

    fun getContext(): Context

    fun showToast(string :String)
}