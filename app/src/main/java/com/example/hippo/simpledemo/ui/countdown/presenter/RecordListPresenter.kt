package com.example.hippo.simpledemo.ui.countdown.presenter

import com.example.hippo.simpledemo.ui.base.presenter.BaseMVPPresenter
import com.example.hippo.simpledemo.ui.countdown.model.RecordModel
import com.example.hippo.simpledemo.ui.countdown.view.RecordListView
import com.vicpin.krealmextensions.queryAll

class RecordListPresenter : BaseMVPPresenter<RecordListView>() {
    fun getRecord() {
        if(isViewAttached)
            mMvpView.setRecord(RecordModel().queryAll())
    }
}