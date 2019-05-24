package info.goodline.reshenie_plus.domain.model

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ChapterRealm: RealmModel {
    @PrimaryKey
    var id: Long = 0
    var name: String = ""
    var tasks: RealmList<TaskRealm>? = null
}