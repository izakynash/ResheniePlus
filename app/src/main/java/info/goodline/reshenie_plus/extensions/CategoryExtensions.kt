package info.goodline.reshenie_plus.extensions

import info.goodline.reshenie_plus.models.Category
import info.goodline.reshenie_plus.models.CategoryRealm

fun CategoryRealm.map2Data() = Category (this.id, this.name)

fun Category.map2Realm(): CategoryRealm {
    val categoryRealm = CategoryRealm()
    categoryRealm.id = this.id
    categoryRealm.name = this.name
    return categoryRealm
}