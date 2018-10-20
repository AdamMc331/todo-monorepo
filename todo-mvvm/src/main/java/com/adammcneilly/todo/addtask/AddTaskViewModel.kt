package com.adammcneilly.todo.addtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo_core.BaseTask

/**
 * This ViewModel is responsible for the business logic around adding a task, such as validating it
 * and emitting whenever a task has been validated.
 *
 * In a real application, we may submit the task right here in the ViewModel, but since we kept
 * it simple by supplying data between activities using Intents, we send out a [validTask] via [MutableLiveData]
 * that anyone can subscribe to and handle there.
 *
 * Note: Unlike the two-way communication of MVP, this class has no reference at all to a view. That
 * is why events such as the [validTask] or [descriptionError] are emitted like events. If we were
 * using RxJava, we may consider publish subjects here instead of live data.
 */
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