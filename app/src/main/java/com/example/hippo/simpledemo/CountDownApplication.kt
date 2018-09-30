package com.example.hippo.simpledemo

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class CountDownApplication: Application (){
    companion object {
        @Volatile lateinit var application: CountDownApplication
            private set
    }

    //initialize config
    override fun onCreate() {
        super.onCreate()

        application = this

        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)
    }
}