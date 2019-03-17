package com.adammcneilly.todo.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.data.TaskViewModel
import com.adammcneilly.todo.databinding.ListItemTaskBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    var tasks: List<Task> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ListItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(tasks[position])
    }

    class TaskViewHolder(private val binding: ListItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = TaskViewModel()

        init {
            binding.viewModel = viewModel
        }

        fun bindTask(task: Task) {
            viewModel.task = task
            binding.executePendingBindings()
        }
    }
}