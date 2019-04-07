package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class BookVH (itemView: View?): RecyclerView.ViewHolder(itemView) {
    var tvCategory: TextView? = null
    init {
        tvCategory = itemView?.findViewById(R.id.tvNameCategory)
    }
}