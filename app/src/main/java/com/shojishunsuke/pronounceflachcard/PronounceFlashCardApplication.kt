package com.shojishunsuke.pronounceflachcard

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class PronounceFlashCardApplication:Application() {
    override fun onCreate() {
        super.onCreate()


        Realm.init(this)
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)








    }
}