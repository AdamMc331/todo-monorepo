package com.adammcneilly.todo.addtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo_core.BaseTask

class AddTaskViewModel : ViewModel() {
    val validTask = MutableLiveData<BaseTask>()
    val descriptionError = MutableLiveData<String>()

    fun submitTask(task: BaseTask) {
        if (validateTask(task)) {
            validTask.value = task
        }
    }

    private fun validateTask(task: BaseTask): Boolean {
        return if (task.description.isEmpty()) {
            descriptionError.value = "Description must not be empty."
            false
        } else {
            true
        }
    }
}