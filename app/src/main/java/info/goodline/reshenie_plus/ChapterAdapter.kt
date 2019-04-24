package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import info.goodline.reshenie_plus.models.Chapter

class ChapterAdapter(private val bookNameArray: MutableList<Chapter>, private val clickListener: ChapterActivity): RecyclerView.Adapter<ChapterAdapter.ChapterVH>() {

    override fun getItemCount() = bookNameArray.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): ChapterVH {
        Log.d(TAG, "Chapter_onCreateViewHolder")
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_view_simple_list, viewGroup, false)
        return ChapterVH(itemView)
    }

    override fun onBindViewHolder(chapterVH: ChapterVH, position: Int) {
        val chapter = bookNameArray[position]
        chapterVH.tvChapterVH?.text = chapter.name
        Log.d(TAG, "Chapter_onBindView")

        chapterVH.itemView.setOnClickListener {
            clickListener.onItemClick(chapter.name)
        }
    }

    class ChapterVH (itemView: View?): RecyclerView.ViewHolder(itemView) {
        var tvChapterVH: TextView? = null
        init {
            tvChapterVH = itemView?.findViewById(R.id.tvNameItem)
        }
    }
}