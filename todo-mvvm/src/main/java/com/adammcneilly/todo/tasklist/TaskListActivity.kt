package com.adammcneilly.todo.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.TaskRepository
import com.adammcneilly.todo.databinding.ActivityTaskListBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * This activity handles all of the UI functionality for displaying tasks. It gets those tasks from our [viewModel].
 */
class TaskListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskListBinding
    private lateinit var viewModel: TaskListViewModel
    private val taskAdapter = TaskAdapter()

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
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initializeRecyclerView()
        setupViewModel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            viewModel.returnedFromAddTask(data)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.tasks.observe(this, Observer(taskAdapter::tasks::set))
    }

    @Subscribe
    fun onNavigationAction(action: NavigationAction) {
        @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
        when (action) {
            NavigationAction.ADD_TASK -> navigateToAddTask()
        }
    }

    private fun initializeRecyclerView() {
        binding.taskList.adapter = taskAdapter
        binding.taskList.layoutManager = LinearLayoutManager(this)
    }

    private fun navigateToAddTask() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    companion object {
        private const val ADD_TASK_REQUEST = 0
    }
}
