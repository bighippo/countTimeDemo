package com.example.hippo.simpledemo.ui.delivery.view

import com.example.hippo.simpledemo.ui.base.view.BaseView
import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel

interface DeliveryListView : BaseView{

    fun onSuccess(model: List<DeliveryModel>)

    fun onError(error:String)
}