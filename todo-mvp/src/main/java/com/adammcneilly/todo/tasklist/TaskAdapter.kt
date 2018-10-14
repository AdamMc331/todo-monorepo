package com.adammcneilly.todo.tasklist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.todo.R
import com.adammcneilly.todo_core.BaseTask

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks: List<BaseTask> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(tasks[position])
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val taskDescriptionTextView = view.findViewById<TextView>(R.id.task_description)

        fun bindTask(task: BaseTask) {
            taskDescriptionTextView.text = task.description
        }
    }
}