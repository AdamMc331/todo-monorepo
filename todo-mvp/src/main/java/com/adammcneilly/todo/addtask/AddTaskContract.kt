package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.data.Task

/**
 * For notes on a contract class, @see [com.adammcneilly.todo.tasklist.TaskListContract]
 */
class AddTaskContract {

    interface View {
        fun getTask(): Task
        fun returnWithTask(task: Task)
        fun showDescriptionError(errorStringRes: Int)
    }

    interface Presenter {
        fun submitButtonClicked()
        fun validateTask(task: Task): Boolean
        fun viewDestroyed()
    }
}