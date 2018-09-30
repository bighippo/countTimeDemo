package com.example.hippo.simpledemo.ui.countdown.view

import com.example.hippo.simpledemo.ui.base.view.MvpView
import com.example.hippo.simpledemo.ui.countdown.model.RecordModel

interface RecordListView : MvpView {
    //fun countdownComplete()

    // fun countdownNext(time:Int)

    fun setRecord(record: List<RecordModel>)
}