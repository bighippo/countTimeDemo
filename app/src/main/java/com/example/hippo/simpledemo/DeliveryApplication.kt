package com.example.hippo.simpledemo

import android.app.Application
import com.example.hippo.simpledemo.network.base.ApiClient

class DeliveryApplication: Application (){
    companion object {
        @Volatile lateinit var application: DeliveryApplication
            private set
    }

    //initialize config
    override fun onCreate() {
        super.onCreate()

        application = this

        //initialize ApiClient
        ApiClient.instance.init()
    }
}