package info.goodline.reshenie_plus.Providers

import android.util.Log
import info.goodline.reshenie_plus.TAG
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.domain.model.model.Category
import info.goodline.reshenie_plus.domain.model.model.CategoryRealm
import io.realm.Realm

class CategoryDBProvider {

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