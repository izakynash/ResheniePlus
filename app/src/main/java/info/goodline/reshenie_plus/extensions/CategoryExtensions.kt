package info.goodline.reshenie_plus.extensions

import info.goodline.reshenie_plus.domain.model.model.Category
import info.goodline.reshenie_plus.domain.model.model.CategoryRealm

fun CategoryRealm.map2Data() = Category(this.id, this.name)

fun Category.map2Realm(): CategoryRealm {
    val categoryRealm = CategoryRealm()
    categoryRealm.id = this.id
    categoryRealm.name = this.name
    return categoryRealm
}