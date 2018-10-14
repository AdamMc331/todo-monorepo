package com.adammcneilly.todo.tasklist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.todo.R
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.TaskRepository
import com.adammcneilly.todo_core.BaseTask
import com.adammcneilly.todo_core.BaseTaskAdapter
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity() {
    private val adapter = BaseTaskAdapter()
    private lateinit var viewModel: TaskListViewModel

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = TaskRepository()
            val viewModel = TaskListViewModel(repository)

            @Suppress("UNCHECKED_CAST")
            return viewModel as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(toolbar)

        setupViewModel()
        initializeRecyclerView()
        initializeFAB()

        viewModel.getTasks()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
            val newTask = BaseTask(description)
            adapter.tasks += newTask
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java)

        viewModel.tasks.observe(this, Observer {
            it?.let(adapter::tasks::set)
        })
    }

    private fun initializeRecyclerView() {
        task_list.adapter = adapter
        task_list.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeFAB() {
        fab.setOnClickListener {
            navigateToAddTask()
        }
    }

    private fun navigateToAddTask() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    companion object {
        private const val ADD_TASK_REQUEST = 0
    }
}
