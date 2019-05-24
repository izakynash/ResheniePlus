package info.goodline.reshenie_plus.Providers

import android.util.Log
import info.goodline.reshenie_plus.TAG
import info.goodline.reshenie_plus.extensions.map2Data
import info.goodline.reshenie_plus.extensions.map2DataList
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.domain.model.Book
import info.goodline.reshenie_plus.domain.model.model.BookRealm
import io.realm.Realm

class BookDBProvider {

    fun saveBook (book: Book?) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(book?.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(BookRealm::class.java).findAll()}")
            realm.commitTransaction()
        }
    }

    fun loadBooks(): MutableList<Book?> {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(BookRealm::class.java)
                .findAll()

            return realm.copyFromRealm(results).map2DataList()
        }
    }

    fun loadBookByName(nameBook: String?): Book? {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(BookRealm::class.java).equalTo("name", nameBook)
                .findFirst()
            return realm.copyFromRealm(results)?.map2Data()
        }
    }

    fun deleteBook(nameBook: String?) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                val result = it.where(BookRealm::class.java)
                    .equalTo("name", nameBook)
                    .findAll()
                result.deleteAllFromRealm()
            }
        }
    }
}

// var bookList: MutableList<Book?> = mutableListOf(
//            Book(name = "Информатика: Теория, вычисления, программирование",
//                describtion = "Учебное пособие для практических и лабораторных работ для студентов вузов / Т.П. Крюкова, И.А. Печерских",
//                link = "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii105.pdf",
//                image = R.drawable.book1
//            ),
//            Book(name = "Информатика. Программирование в системе Turbo  Pascal",
//                describtion = "Практикум / Г.Е. Иванец, О.А. Ивина; Кемеровский технологический институт пищевой промышленности",
//                link = "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii106.pdf",
//                image = R.drawable.book2
//            )
//        )