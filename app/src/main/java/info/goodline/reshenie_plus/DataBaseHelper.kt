package info.goodline.reshenie_plus

import android.util.Log
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.extensions.map2Data
import info.goodline.reshenie_plus.models.Category
import info.goodline.reshenie_plus.models.CategoryRealm
import io.realm.Realm
import io.realm.RealmConfiguration

class DataBaseHelper {

    companion object {
        private val categoryList = mutableListOf(
            Category(1, "Информатика"),
            Category(2, "Геометрия и инженерная графика")
        )

        fun getAllCategory() = categoryList
    }

    fun saveCategory (category: Category) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(category.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(CategoryRealm::class.java).findAll()}")
            realm.commitTransaction()
        }
    }
}