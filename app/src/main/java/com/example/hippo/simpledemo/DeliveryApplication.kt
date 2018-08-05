package com.example.hippo.simpledemo

import android.app.Application
import com.example.hippo.simpledemo.network.base.ApiClient

class DeliveryApplication: Application (){

    //initialize config
    override fun onCreate() {
        super.onCreate()

        //initialize ApiClient
        ApiClient.instance.init()
    }
}