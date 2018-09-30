package com.example.hippo.simpledemo.ui.countdown.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RecordModel : RealmObject() {
    @PrimaryKey
    var taskName: String = ""

    var start: Int? = null

     var end: Int? = null
}