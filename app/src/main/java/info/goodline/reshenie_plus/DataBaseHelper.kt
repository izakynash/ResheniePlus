package info.goodline.reshenie_plus

import android.util.Log
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.extensions.map2Data
import info.goodline.reshenie_plus.extensions.map2DataList
import info.goodline.reshenie_plus.models.*
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*

class DataBaseHelper {

    companion object {
        private val categoryList = mutableListOf(
            Category(1, "Информатика"),
            Category(2, "Геометрия и инженерная графика")
        )

        var chapterList: MutableList<Chapter> = mutableListOf(
        )

        var bookList = mutableListOf(
            Book(1,
                "Информатика: Теория, вычисления, программирование",
                "Учебное пособие для практических и лабораторных работ для студентов вузов / Т.П. Крюкова, И.А. Печерских",
                "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii105.pdf",
                R.drawable.book1
            ),
            Book(2,
                "Информатика. Программирование в системе Turbo  Pascal",
                "Практикум / Г.Е. Иванец, О.А. Ивина; Кемеровский технологический институт пищевой промышленности",
                "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii106.pdf",
                R.drawable.book2
            )
        )

        fun getAllCategory() = categoryList
    }

    fun saveBook (book: Book) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(book.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(BookRealm::class.java).findAll()}")
            realm.commitTransaction()
        }
    }

    fun saveCategory (category: Category) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(category.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(CategoryRealm::class.java).findAll()}")
            realm.commitTransaction()
        }
    }

    fun loadBooks(): MutableList<Book> {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(BookRealm::class.java)
                .findAll()

            return realm.copyFromRealm(results).map2DataList()
        }
    }


}