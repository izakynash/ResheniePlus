package info.goodline.reshenie_plus.domain.model.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CategoryRealm: RealmModel {
    @PrimaryKey
    var id: Long = 0
    var name: String = ""
}