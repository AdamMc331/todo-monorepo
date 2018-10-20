package com.adammcneilly.todo.addtask

import com.adammcneilly.todo_core.BaseTask

/**
 * This is an implementation of an [AddTaskContract.Presenter] which handles the business logic
 * for adding a task that the [view] should not be responsible for.
 *
 * Note, we use dependency injection for the [view] here so that the presenter is easily unit testable
 * by supplying a mock view.
 */
class AddTaskPresenter(private val view: AddTaskContract.View) : AddTaskContract.Presenter {
    override fun submitButtonClicked() {
        val task = view.getTask()

        if (validateTask(task)) {
            view.returnWithTask(task)
        } else {
            view.showInvalidDescriptionError()
        }
    }

    override fun validateTask(task: BaseTask): Boolean {
        return task.description.isNotEmpty()
    }
}