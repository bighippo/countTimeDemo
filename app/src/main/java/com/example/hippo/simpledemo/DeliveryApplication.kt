package com.example.hippo.simpledemo

import android.app.Application
import com.example.hippo.simpledemo.network.base.ApiClient
import com.example.hippo.simpledemo.widget.fresco.FrescoHttpsSupport
import com.facebook.drawee.backends.pipeline.Fresco

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

        //init Fresco
        val frescoSupport = FrescoHttpsSupport()
        Fresco.initialize(this, frescoSupport.getConfig(this))
    }
}