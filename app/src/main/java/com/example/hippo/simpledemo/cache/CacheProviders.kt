package com.example.hippo.simpledemo.cache

import com.example.hippo.simpledemo.DeliveryApplication
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker


object CacheProviders {

    private var deliveryCacheProviders: DeliveryCacheProviders = RxCache.Builder()
            .persistence(DeliveryApplication.application.externalCacheDir!!, GsonSpeaker())
            .using(DeliveryCacheProviders::class.java)

    //set cache interface
    val deliveryCache: DeliveryCacheProviders
        get() {
            return this.deliveryCacheProviders
        }
}
