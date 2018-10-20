package com.adammcneilly.todo.tasklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.data.TaskRepository
import com.adammcneilly.todo_core.BaseTask

/**
 * This class is responsible for any business logic around displaying a list of tasks. The repository
 * is supplied via dependency injection to make this testable and easy to mock.
 *
 * We also make use of [MutableLiveData] so that we can manage the tasks in a lifecycle conscious way.
 * If the screen is rotated, we won't need to pull for new tasks because Android maintains the data for us.
 * We only pull for tasks if we don't already have any, see [getTasks].
 */
class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks = MutableLiveData<List<BaseTask>>()

    fun getTasks() {
        if (tasks.value == null) {
            tasks.value = repository.getItems()
        }
    }
}