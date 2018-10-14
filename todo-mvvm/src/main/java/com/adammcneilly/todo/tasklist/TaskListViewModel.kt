package com.adammcneilly.todo.tasklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.Task
import com.adammcneilly.todo.data.Repository

class TaskListViewModel(private val repository: Repository<Task>) : ViewModel() {
    val tasks = MutableLiveData<List<Task>>()

    fun getTasks() {
        tasks.value = repository.getItems()
    }
}