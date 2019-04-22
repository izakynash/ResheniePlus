package info.goodline.reshenie_plus.models

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

//@RealmClass
open class CategoryRealm: RealmModel {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
}