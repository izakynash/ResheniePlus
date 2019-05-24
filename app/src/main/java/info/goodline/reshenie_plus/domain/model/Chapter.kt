package info.goodline.reshenie_plus.domain.model

data class Chapter (var id: Long,
                    var name: String,
                    var tasks: MutableList<Task>? = null)

