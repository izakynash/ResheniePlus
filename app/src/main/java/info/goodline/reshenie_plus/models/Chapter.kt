package info.goodline.reshenie_plus.models

data class Chapter (var id: Long,
                    var name: String,
                    var tasks: MutableList<Task>? = null)

