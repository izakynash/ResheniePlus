package info.goodline.reshenie_plus.models

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class TaskRealm: RealmModel {
    @PrimaryKey
    var number: Int = 0
    var id: Int = 0
    var count: Int = 0
}