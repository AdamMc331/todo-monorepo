package com.adammcneilly.todo.tasklist

import com.adammcneilly.todo_core.BaseTask

class TaskListContract {
    interface View {
        fun showTasks(tasks: List<BaseTask>)
        fun navigateToAddTask()
    }

    interface Presenter {
        fun getTasks()
        fun addButtonClicked()
    }

    interface Model {
        fun getTasks(): List<BaseTask>
    }
}