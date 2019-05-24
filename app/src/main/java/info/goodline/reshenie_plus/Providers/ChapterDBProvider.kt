package info.goodline.reshenie_plus.Providers

import android.util.Log
import info.goodline.reshenie_plus.TAG
import info.goodline.reshenie_plus.extensions.map2Data
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.domain.model.Chapter
import info.goodline.reshenie_plus.domain.model.ChapterRealm
import io.realm.Realm

class ChapterDBProvider {
    fun saveChapter (chapter: Chapter?) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.copyFromRealm(realm.copyToRealmOrUpdate(chapter?.map2Realm()))
            Log.d(TAG, "Realm.getDefaultInstance ${realm.where(ChapterRealm::class.java).findAll()}")
            realm.commitTransaction()
        }
    }

    fun loadChapterByName(nameChapter: String?): Chapter? {
        Realm.getDefaultInstance().use { realm ->
            val results = realm
                .where(ChapterRealm::class.java).equalTo("name", nameChapter)
                .findFirst()
            return realm.copyFromRealm(results)?.map2Data()
        }
    }
}