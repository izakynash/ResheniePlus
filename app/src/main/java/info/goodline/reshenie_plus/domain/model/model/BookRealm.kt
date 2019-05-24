package info.goodline.reshenie_plus.domain.model.model

import info.goodline.reshenie_plus.domain.model.ChapterRealm
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class BookRealm: RealmModel {
    @PrimaryKey
    var id: Long = 0
    var name: String? = ""
    var description: String? = ""
    var link: String? = ""
    var image: Int = 0
    var chapters: RealmList<ChapterRealm>? = null
}