package info.goodline.reshenie_plus.extensions

import info.goodline.reshenie_plus.models.*
import io.realm.RealmList

fun ChapterRealm.map2Data() = Chapter (this.id, this.name, this.tasks?.map2DataList())

fun Chapter.map2Realm(): ChapterRealm {
    val chapterRealm = ChapterRealm()
    chapterRealm.id = this.id
    chapterRealm.name = this.name
    chapterRealm.tasks = this.tasks?.map2RealmList()
    return chapterRealm
}

fun MutableList<Chapter>.map2RealmList(): RealmList<ChapterRealm> {
    val rl = RealmList<ChapterRealm>()
    this.forEach { rl.add(it.map2Realm()) }
    return rl
}

fun MutableList<ChapterRealm>.map2DataList(): MutableList<Chapter> {
    val l = ArrayList<Chapter>()
    this.forEach { l.add(it.map2Data()) }
    return l
}