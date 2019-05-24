package info.goodline.reshenie_plus

import android.support.multidex.MultiDexApplication
import info.goodline.reshenie_plus.domain.model.Migration
import io.realm.Realm
import io.realm.RealmConfiguration

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .schemaVersion(Migration.SCHEMA_VERSION)
            .migration(Migration()) // Migration to run instead of throwing an exception
            .build()
    }
}