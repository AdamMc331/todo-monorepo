package com.adammcneilly.todo_core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.todo_core.R

class BaseTaskAdapter : RecyclerView.Adapter<BaseTaskAdapter.BaseTaskViewHolder>() {
    var tasks: List<BaseTask> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item_task, parent, false)
        return BaseTaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: BaseTaskViewHolder, position: Int) {
        holder.bindTask(tasks[position])
    }

    class BaseTaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val taskDescriptionTextView = view.findViewById<TextView>(R.id.task_description)

        fun bindTask(task: BaseTask) {
            taskDescriptionTextView.text = task.description
        }
    }
}