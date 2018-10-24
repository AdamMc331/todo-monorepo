package com.adammcneilly.todo.tasklist

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.TaskRepository
import com.adammcneilly.todo_core.BaseTask

/**
 * This class is responsible for any business logic around displaying a list of tasks. The repository
 * is supplied via dependency injection to make this testable and easy to mock.
 *
 * We also make use of [MutableLiveData] so that we can manage the tasks in a lifecycle conscious way.
 * If the screen is rotated, we won't need to pull for new tasks because Android maintains the data for us.
 * We only pull for tasks if we don't already have any, see [getTasks].
 *
 * @property[newTask] This LiveData is updated any time a new task is added.
 */
class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks = MutableLiveData<List<BaseTask>>()
    val newTask = MutableLiveData<BaseTask>()
    val navigationAction = MutableLiveData<NavigationAction>()

    fun getTasks() {
        if (tasks.value == null) {
            tasks.value = repository.getItems()
        }
    }

    fun returnedFromAddTask(data: Intent?) {
        val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
        val taskFromIntent = BaseTask(description)
        newTask.value = taskFromIntent
    }

    fun addButtonClicked() {
        navigationAction.value = NavigationAction.ADD_TASK
    }
}