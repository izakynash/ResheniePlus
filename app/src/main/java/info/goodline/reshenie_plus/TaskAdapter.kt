package info.goodline.reshenie_plus

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import info.goodline.reshenie_plus.domain.model.Task

class TaskAdapter(private val taskNumberArray: MutableList<Task>?): RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    override fun getItemCount() = taskNumberArray!!.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewInt: Int): TaskVH {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_view_numbers, viewGroup, false)
        return TaskVH(itemView)
    }

    override fun onBindViewHolder(VH: TaskVH, position: Int) {
        val taskNumber = taskNumberArray!![position]
        VH.tvTaskVH?.text = taskNumber.number.toString()
    }

    class TaskVH (itemView: View?): RecyclerView.ViewHolder(itemView) {
        var tvTaskVH: TextView? = null
        init {
            tvTaskVH = itemView?.findViewById(R.id.tvNumberItem)
        }
    }

    fun insertItem(item: Task) {
        taskNumberArray?.add(item)
        notifyItemInserted(taskNumberArray!!.size-1)
    }
}