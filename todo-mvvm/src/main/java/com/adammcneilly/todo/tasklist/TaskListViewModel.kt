package com.adammcneilly.todo.tasklist

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.data.TaskRepository
import org.greenrobot.eventbus.EventBus

/**
 * This class is responsible for any business logic around displaying a list of tasks. The repository
 * is supplied via dependency injection to make this testable and easy to mock.
 *
 * We also make use of [MutableLiveData] so that we can manage the tasks in a lifecycle conscious way.
 * If the screen is rotated, we won't need to pull for new tasks because Android maintains the data for us.
 * We only pull for tasks if we don't already have any, see [fetchTasks].
 */
class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        if (_tasks.value == null) {
            _tasks.value = repository.getItems()
        }
    }

    fun returnedFromAddTask(data: Intent?) {
        val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
        val taskFromIntent = Task(description)
        val currentTaskList = _tasks.value.orEmpty()
        _tasks.value = currentTaskList + taskFromIntent
    }

    fun addButtonClicked() {
        EventBus.getDefault().post(NavigationAction.ADD_TASK)
    }
}