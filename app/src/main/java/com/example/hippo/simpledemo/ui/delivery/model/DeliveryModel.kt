package com.example.hippo.simpledemo.ui.delivery.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DeliveryModel : Serializable {
    @Expose
    @SerializedName("description")
    var description: String = ""

    @Expose
    @SerializedName("imageUrl")
    var imageUrl: String = ""

    @Expose
    @SerializedName("location")
    var location = LocationModel()
}