package com.adammcneilly.todo.addtask

import com.adammcneilly.todo_core.BaseTask

class AddTaskContract {
    interface View {
        fun getTask(): BaseTask
        fun submitTask(task: BaseTask)
        fun showInvalidDescriptionError()
    }

    interface Presenter {
        fun submitButtonClicked()
        fun validateTask(task: BaseTask): Boolean
    }
}