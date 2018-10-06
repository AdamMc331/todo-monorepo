package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.Task

class AddTaskPresenter(private val view: AddTaskContract.View) : AddTaskContract.Presenter {
    override fun submitButtonClicked() {
        val task = view.getTask()

        if (validateTask(task)) {
            view.submitTask(task)
        } else {
            view.showInvalidDescriptionError()
        }
    }

    override fun validateTask(task: Task): Boolean {
        return task.description.isNotEmpty()
    }
}