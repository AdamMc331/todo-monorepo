package com.adammcneilly.todo.tasklist

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

    override fun getTasks() {
        view?.showTasks(model.getTasks())
    }

    override fun viewCreated() {
        getTasks()
    }

    override fun viewDestroyed() {
        view = null
    }
}