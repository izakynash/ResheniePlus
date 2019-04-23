package info.goodline.reshenie_plus.models

class Book (val id: Int, val name: String, var describtion: String, var link: String, var image: Int, var chapters: MutableList<Chapter>? = null)

