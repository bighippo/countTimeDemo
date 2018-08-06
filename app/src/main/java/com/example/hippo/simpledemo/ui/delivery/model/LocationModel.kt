package com.example.hippo.simpledemo.ui.delivery.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class LocationModel : Serializable{
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