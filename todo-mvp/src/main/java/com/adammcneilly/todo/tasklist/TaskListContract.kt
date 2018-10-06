package com.adammcneilly.todo.tasklist

import com.adammcneilly.todo.Task

class TaskListContract {
    interface View {
        fun showTasks(tasks: List<Task>)
        fun navigateToAddTask()
    }

    interface Presenter {
        fun getTasks()
        fun addButtonClicked()
    }

    interface Model {
        fun getTasks(): List<Task>
    }
}