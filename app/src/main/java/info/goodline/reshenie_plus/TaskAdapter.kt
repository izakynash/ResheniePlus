package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import info.goodline.reshenie_plus.models.Chapter
import info.goodline.reshenie_plus.models.Task

class TaskAdapter(private val taskNumberArray: MutableList<Task>?): RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    override fun getItemCount() = taskNumberArray!!.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): TaskVH {
        Log.d(TAG, "Task_onCreateViewHolder")
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_simple_list, viewGroup, false)
        return TaskVH(itemView)
    }

    override fun onBindViewHolder(VH: TaskVH, position: Int) {
        val taskNumber = taskNumberArray!![position]
        VH.tvTaskVH?.text = taskNumber.number.toString()
    }

    class TaskVH (itemView: View?): RecyclerView.ViewHolder(itemView) {
        var tvTaskVH: TextView? = null
        init {
            tvTaskVH = itemView?.findViewById(R.id.tvNameItem)
        }
    }

    fun insertItem(item: Task) {
        taskNumberArray?.add(0, item)
        Log.d(TAG, "insert")
        notifyItemInserted(0)
        Log.d(TAG, "notify")
    }
}