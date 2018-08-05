package com.example.hippo.simpledemo.ui.delivery.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocationModel{
    @Expose
    @SerializedName("lat")
    var lat: String = ""

    @Expose
    @SerializedName("lng")
    var lng: String = ""

    @Expose
    @SerializedName("address")
    var address: String = ""
}