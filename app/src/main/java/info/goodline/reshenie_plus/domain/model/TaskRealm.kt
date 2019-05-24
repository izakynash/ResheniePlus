package info.goodline.reshenie_plus.domain.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class TaskRealm: RealmModel {
    @PrimaryKey
    var id: Long = 0
    var number: Int = 0
    var count: Int = 0
}