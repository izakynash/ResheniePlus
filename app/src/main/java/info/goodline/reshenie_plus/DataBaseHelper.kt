package info.goodline.reshenie_plus

import android.support.annotation.NonNull
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

        var chapterList: MutableList<Chapter?> = mutableListOf(
        )

        var bookList: MutableList<Book?> = mutableListOf(
            Book(name = "Информатика: Теория, вычисления, программирование",
                describtion = "Учебное пособие для практических и лабораторных работ для студентов вузов / Т.П. Крюкова, И.А. Печерских",
                link = "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii105.pdf",
                image = R.drawable.book1
            ),
            Book(name = "Информатика. Программирование в системе Turbo  Pascal",
                describtion = "Практикум / Г.Е. Иванец, О.А. Ивина; Кемеровский технологический институт пищевой промышленности",
                link = "Электронная версия книги:\nhttp://e-lib.kemtipp.ru/uploads/29/pmii106.pdf",
                image = R.drawable.book2
            )
        )

        fun getAllCategory() = categoryList
    }

    fun saveBook (book: Book?) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(book?.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(BookRealm::class.java).findAll()}")
            realm.commitTransaction()
        }
    }

    fun saveChapter (chapter: Chapter?) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(chapter?.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(ChapterRealm::class.java).findAll()}")
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

    fun loadBooks(): MutableList<Book?> {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(BookRealm::class.java)
                .findAll()

            return realm.copyFromRealm(results).map2DataList()
        }
    }

    fun deleteBook(book: Book?) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                val result = it.where(BookRealm::class.java)
                    .equalTo("id", book?.id)
                    .findAll()
                result.deleteAllFromRealm()
            }
        }
    }
}