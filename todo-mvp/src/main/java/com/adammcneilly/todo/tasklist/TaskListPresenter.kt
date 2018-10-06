package com.adammcneilly.todo.tasklist

class TaskListPresenter(
        private val view: TaskListContract.View,
        private val model: TaskListContract.Model
) : TaskListContract.Presenter {

    override fun addButtonClicked() {
        view.navigateToAddTask()
    }

    override fun getTasks() {
        view.showTasks(model.getTasks())
    }
}