package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import info.goodline.reshenie_plus.domain.model.Book

class AllBookAdapter(var booksArray: MutableList<Book?>, private val clickListener: AllBooksActivity): RecyclerView.Adapter<AllBookAdapter.AllBooksVH>() {

    interface onItemClickListener {
        fun onItemClick(nameItem: String?)
        fun onItemDelete(nameBook: String?, position: Int)
    }

    override fun getItemCount() = booksArray.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): AllBooksVH {
        val itemViewBooks =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_books, viewGroup, false)
        return AllBooksVH(itemViewBooks)
    }

    override fun onBindViewHolder(allBooksVH: AllBooksVH, position: Int) {
        val book: Book? = booksArray[position]

        allBooksVH.tvNameBookVH?.text = book?.name
        allBooksVH.tvDescribeVH?.text = book?.describtion
        allBooksVH.tvLinkVH?.text = "Электронная версия книги:\n ${book?.link}"
        allBooksVH.ivImageVH?.setImageResource(R.drawable.ic_baseline_book)

        allBooksVH.itemView.setOnClickListener {
            clickListener.onItemClick(book?.name)
        }

        allBooksVH.btnDeleteBookVH?.setOnClickListener {
            clickListener.onItemDelete(book?.name, position)
        }
    }

    class AllBooksVH(itemViewBooks: View?) : RecyclerView.ViewHolder(itemViewBooks) {
        var tvNameBookVH: TextView? = null
        var tvDescribeVH: TextView? = null
        var tvLinkVH: TextView? = null
        var ivImageVH: ImageView? = null
        var btnDeleteBookVH: Button? = null

        init {
            tvNameBookVH = itemViewBooks?.findViewById(R.id.tvNameBook)
            tvDescribeVH = itemViewBooks?.findViewById(R.id.tvDescribe)
            tvLinkVH = itemViewBooks?.findViewById(R.id.tvLink)
            ivImageVH = itemViewBooks?.findViewById(R.id.ivImage)
            btnDeleteBookVH = itemViewBooks?.findViewById(R.id.btnDeleteBook)
        }
    }

    fun insertItem(item: Book?) {
        booksArray.add(item)
        notifyItemInserted(booksArray.size-1)
    }


    fun removeAt(position: Int) {
        booksArray.removeAt(position)
        notifyItemRemoved(position)
    }
//
//    fun updateItemChat(item: Book) {
//        // Проверяем есть ли такой элемент в списке
//        if (!booksArray.contains(item)) {
//            // Если нет то ничего не обновлять
//            return
//        }
//        // Если есть, то обновить его
//        val positionItem = booksArray.indexOf(item)
//        booksArray[positionItem] = item
//        notifyItemChanged(positionItem)
//    }
//
//    fun getItemChat(position: Int): Book? {
//        if (position >= itemCount) {
//            return null
//        }
//
//        return booksArray[position]
//    }

}

