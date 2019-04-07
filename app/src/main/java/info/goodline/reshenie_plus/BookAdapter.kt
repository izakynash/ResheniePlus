package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter

class BookAdapter(private val values: List<Category>): RecyclerView.Adapter<BookVH>() {

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        val category: Category = values[position]
        holder.tvCategory?.text = category.name
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): BookVH {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_category, viewGroup, false)
        return BookVH(itemView)
    }
}
