package info.goodline.reshenie_plus.extensions

import info.goodline.reshenie_plus.domain.model.Book
import info.goodline.reshenie_plus.domain.model.model.BookRealm
import io.realm.RealmList

fun BookRealm.map2Data() = Book (this.id, this.name, this.description, this.link, this.image, this.chapters?.map2DataList())

fun Book.map2Realm(): BookRealm {
    val bookRealm = BookRealm()
    bookRealm.id = this.id
    bookRealm.name = this.name
    bookRealm.description = this.describtion
    bookRealm.link = this.link
    bookRealm.image = this.image
    bookRealm.chapters = this.chapters?.map2RealmList()

    return bookRealm
}

fun MutableList<Book>.map2RealmList(): RealmList<BookRealm> {
    val rl = RealmList<BookRealm>()
    this.forEach { rl.add(it.map2Realm()) }
    return rl
}

fun MutableList<BookRealm>.map2DataList(): MutableList<Book?> {
    val l = ArrayList<Book?>()
    this.forEach { l.add(it.map2Data()) }
    return l
}