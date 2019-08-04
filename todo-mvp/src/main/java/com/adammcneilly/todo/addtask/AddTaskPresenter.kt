package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.R
import com.adammcneilly.todo.data.Task

/**
 * This is an implementation of an [AddTaskContract.Presenter] which handles the business logic
 * for adding a task that the [view] should not be responsible for.
 *
 * Note, we use dependency injection for the [view] here so that the presenter is easily unit testable
 * by supplying a mock view.
 */
class AddTaskPresenter(private var view: AddTaskContract.View?) : AddTaskContract.Presenter {
    override fun submitButtonClicked() {
        val task = view?.getTask()

        if (task != null && validateTask(task)) {
            view?.returnWithTask(task)
        } else {
            view?.showDescriptionError(R.string.description_must_not_be_empty)
        }
    }

    override fun validateTask(task: Task): Boolean {
        return task.description.isNotEmpty()
    }

    override fun viewDestroyed() {
        view = null
    }
}