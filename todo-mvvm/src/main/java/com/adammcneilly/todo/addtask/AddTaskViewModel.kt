package com.adammcneilly.todo.addtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.Task

class AddTaskViewModel : ViewModel() {
    val validTask = MutableLiveData<Task>()
    val descriptionError = MutableLiveData<String>()

    fun submitTask(task: Task) {
        if (validateTask(task)) {
            validTask.value = task
        }
    }

    private fun validateTask(task: Task): Boolean {
        return if (task.description.isEmpty()) {
            descriptionError.value = "Description must not be empty."
            false
        } else {
            true
        }
    }
}