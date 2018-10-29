package com.adammcneilly.todo.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.data.TaskRepository

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: LiveData<List<Task>>
        get() = repository.getTasks()

    val navigationAction = MutableLiveData<NavigationAction>()

    fun addButtonClicked() {
        navigationAction.value = NavigationAction.ADD_TASK
    }
}