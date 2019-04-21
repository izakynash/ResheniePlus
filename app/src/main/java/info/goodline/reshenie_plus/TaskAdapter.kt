package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TaskAdapter(private val taskNumberArray: List<String>): RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    override fun getItemCount() = taskNumberArray.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): TaskVH {
        Log.d(TAG, "Task_onCreateViewHolder")
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_simple_list, viewGroup, false)
        return TaskVH(itemView)
    }

    override fun onBindViewHolder(VH: TaskVH, position: Int) {
        val taskNumber: String = taskNumberArray[position]
        VH.tvTaskVH?.text = taskNumber
        Log.d(TAG, "Task_onBindView")

//        VH.itemView.setOnClickListener {
//            clickListener.onItemClick(chapterName)
//        }
    }

    class TaskVH (itemView: View?): RecyclerView.ViewHolder(itemView) {
        var tvTaskVH: TextView? = null
        init {
            tvTaskVH = itemView?.findViewById(R.id.tvNameItem)
        }
    }
}