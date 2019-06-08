package info.goodline.reshenie_plus.models

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmModule
import io.realm.annotations.Required

@RealmClass
open class CategoryRealm: RealmModel {
    @PrimaryKey
    var id: Long = 0
    var name: String = ""
}