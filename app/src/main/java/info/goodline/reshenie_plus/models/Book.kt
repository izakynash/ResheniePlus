package info.goodline.reshenie_plus.models

class Book (val id: Int = 0, val name: String, var describtion: String, var link: String, var image: Int = 0, var chapters: MutableList<Chapter>? = null)

