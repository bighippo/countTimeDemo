package com.example.hippo.simpledemo.network.api

import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel
import io.reactivex.Observable
import retrofit2.http.*

interface DeliveryApi{

    //use this api to get deliveries
    @GET("deliveries")
    fun getDeliveryList(@Query("offset") offset:Int): Observable<List<DeliveryModel>>
}