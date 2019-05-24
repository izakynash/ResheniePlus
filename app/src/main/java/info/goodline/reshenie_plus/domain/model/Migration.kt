package info.goodline.reshenie_plus.domain.model

import io.realm.DynamicRealm
import io.realm.RealmMigration

class Migration: RealmMigration {

    companion object {
        const val SCHEMA_VERSION = 0L
    }

    override fun migrate (realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

//        val schema = realm.schema

//        if (oldVersion == 0L) {
//            schema.create("Person")
//                .addField(
//                    "name", String.class)
//            oldVersion++;
//        }
    }
}
