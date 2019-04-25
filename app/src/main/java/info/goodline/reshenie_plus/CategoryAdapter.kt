package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import info.goodline.reshenie_plus.extensions.map2Realm
import info.goodline.reshenie_plus.models.Category
import info.goodline.reshenie_plus.models.CategoryRealm
import info.goodline.reshenie_plus.models.Migration
import io.realm.Realm
import io.realm.RealmConfiguration

class CategoryAdapter(private val categoryList: MutableList<Category>, private val clickListener: CategoryActivity): RecyclerView.Adapter<CategoryAdapter.BookVH>() {

    interface onItemClickListener {
        fun onItemClick(nameItem: String?)
    }

    override fun getItemCount() = categoryList.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): BookVH {
        Log.d(TAG, "Category_onCreateViewHolder")
        val itemViewCategory = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_view_simple_list, viewGroup, false)
        return BookVH(itemViewCategory)
    }

    override fun onBindViewHolder(bookVH: BookVH, position: Int) {
        val categoryName = categoryList[position]
        bookVH.tvCategoryVH?.text = categoryName.name

        val dataBaseHelper = DataBaseHelper()
        dataBaseHelper.saveCategory(categoryName)

        bookVH.itemView.setOnClickListener {
            clickListener.onItemClick(categoryName.name)
        }
    }

    class BookVH (itemViewCategory: View?): RecyclerView.ViewHolder(itemViewCategory) {
        var tvCategoryVH: TextView? = null
        init {
            tvCategoryVH = itemViewCategory?.findViewById(R.id.tvNameItem)
        }
    }
}