package com.adammcneilly.todo.tasklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.data.Repository
import com.adammcneilly.todo_core.BaseTask

class TaskListViewModel(private val repository: Repository<BaseTask>) : ViewModel() {
    val tasks = MutableLiveData<List<BaseTask>>()

    fun getTasks() {
        tasks.value = repository.getItems()
    }
}