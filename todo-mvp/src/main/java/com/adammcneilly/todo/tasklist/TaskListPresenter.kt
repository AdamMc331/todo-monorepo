package com.adammcneilly.todo.tasklist

import android.content.Intent
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.Task

/**
 * The presenter handles the business logic for displaying a list of tasks.
 *
 * Notice that dependency injection is used to supply a [view] and [model]. This helps enforce a
 * separation of concerns between the components, but also allows for testability so that we can
 * mock these parameters in unit tests.
 */
class TaskListPresenter(
        private var view: TaskListContract.View?,
        private val model: TaskListContract.Model
) : TaskListContract.Presenter {

    override fun addButtonClicked() {
        view?.navigateToAddTask()
    }

    override fun viewCreated() {
        val tasks = model.getTasks()
        view?.showTasks(tasks)
    }

    override fun viewDestroyed() {
        view = null
    }

    override fun returnedFromAddTask(data: Intent?) {
        val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
        val newTask = Task(description)
        view?.addTask(newTask)
    }

}