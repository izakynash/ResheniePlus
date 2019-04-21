package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CategoryAdapter(private val bookNameArray: List<String>, private val clickListener: onItemClickListener): RecyclerView.Adapter<CategoryAdapter.BookVH>() {

    interface onItemClickListener {
        fun onItemClick(item: String)
    }

    override fun getItemCount() = bookNameArray.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): BookVH {
        Log.d(TAG, "Category_onCreateViewHolder")
        val itemViewCategory = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_view_simple_list, viewGroup, false)
        return BookVH(itemViewCategory)
    }

    override fun onBindViewHolder(bookVH: BookVH, position: Int) {
        val categoryName: String = bookNameArray[position]
        bookVH.tvCategoryVH?.text = categoryName
        Log.d(TAG, "Category_onBindView")

        bookVH.itemView.setOnClickListener {
            clickListener.onItemClick(categoryName)
        }
    }

    class BookVH (itemViewCategory: View?): RecyclerView.ViewHolder(itemViewCategory) {
        var tvCategoryVH: TextView? = null
        init {
            tvCategoryVH = itemViewCategory?.findViewById(R.id.tvNameItem)
        }
    }
}