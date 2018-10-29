package com.adammcneilly.todo.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.R
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.TaskDatabase
import com.adammcneilly.todo.data.TaskRepository
import kotlinx.android.synthetic.main.activity_task_list.*


class TaskListActivity : AppCompatActivity() {
    private val adapter = TaskAdapter()
    private lateinit var viewModel: TaskListViewModel

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val database = TaskDatabase.getInMemoryDatabase(this@TaskListActivity)
            val repository = TaskRepository(database)
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            //TODO: viewModel.returnedFromAddTask(data)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java)

        viewModel.tasks.observe(this, Observer {
            it?.let(adapter::tasks::set)
        })

        viewModel.navigationAction.observe(this, Observer {
            @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
            when (it) {
                NavigationAction.ADD_TASK -> navigateToAddTask()
            }
        })
    }

    private fun initializeRecyclerView() {
        task_list.adapter = adapter
        task_list.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeFAB() {
        fab.setOnClickListener {
            viewModel.addButtonClicked()
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
