package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.Task

class AddTaskContract {
    interface View {
        fun getTask(): Task
        fun submitTask(task: Task)
        fun showInvalidDescriptionError()
    }

    interface Presenter {
        fun submitButtonClicked()
        fun validateTask(task: Task): Boolean
    }
}