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
        val categoryList = mutableListOf(
            Category(1, "Информатика"),
            Category(2, "Геометрия и инженерная графика")
        )

        fun getAllCategory() = categoryList
    }


//    fun loadChats(): MutableList<Category> {
//        Realm.getDefaultInstance().use { realm ->
//            val results = realm
//                .where(CategoryRealm::class.java)
//                .findAll()
//            return realm.copyFromRealm(results).mapto
//        }
//    }

    fun saveAllCategory (category: Category, config: RealmConfiguration) {
        Log.d(TAG, "saveCategory1")
        Realm.getInstance(config).use { realm ->
            realm.beginTransaction()
            Log.d(TAG, "saveCategory2")
            realm.copyFromRealm(realm.copyToRealmOrUpdate(category.map2Realm()))
            // realm.copyFromRealm(realm.copyToRealmOrUpdate(category.map2Realm()))
            // realm.copyFromRealm(realm.copyToRealmOrUpdate(category.map2Realm()))
            Log.d(TAG, "saveCategory3")
            realm.commitTransaction()
        }
    }
}