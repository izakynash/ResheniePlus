package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AllBookAdapter(private var booksArray: List<Books?>): RecyclerView.Adapter<AllBookAdapter.AllBooksVH>() {

    override fun getItemCount() = booksArray.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): AllBooksVH {
        Log.d(TAG, "onCreateViewHolder")
        val itemViewBooks =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_books, viewGroup, false)
        return AllBooksVH(itemViewBooks)
    }

    override fun onBindViewHolder(allBooksVH: AllBooksVH, position: Int) {
        val book: Books? = booksArray[position]

        allBooksVH.tvNameBookVH?.text = book?.name
        allBooksVH.tvDescribeVH?.text = book?.describe
        allBooksVH.tvLinkVH?.text = book?.link
        // временно на новые книжки ставим общую рабочую картинку:
        if (position > 1) allBooksVH.ivImageVH?.setImageResource(R.drawable.ic_baseline_book)
        else allBooksVH.ivImageVH?.setImageResource(book?.image!!)

        Log.d(TAG, "onBindView")
    }


    class AllBooksVH(itemViewBooks: View?) : RecyclerView.ViewHolder(itemViewBooks) {
        var tvNameBookVH: TextView? = null
        var tvDescribeVH: TextView? = null
        var tvLinkVH: TextView? = null
        var ivImageVH: ImageView? = null

        init {
            tvNameBookVH = itemViewBooks?.findViewById(R.id.tvNameBook)
            tvDescribeVH = itemViewBooks?.findViewById(R.id.tvDescribe)
            tvLinkVH = itemViewBooks?.findViewById(R.id.tvLink)
            ivImageVH = itemViewBooks?.findViewById(R.id.ivImage)
        }
    }
}