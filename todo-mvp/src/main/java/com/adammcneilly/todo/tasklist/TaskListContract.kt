package com.adammcneilly.todo.tasklist

import com.adammcneilly.todo_core.BaseTask

/**
 * In MVP, a contract class can define the required behavior of the [Model], [View], and [Presenter].
 */
class TaskListContract {

    /**
     * The view is responsible for any behavior that belongs to the UI. In this case that includes
     * displaying a list, and navigating to a new view.
     */
    interface View {
        fun showTasks(tasks: List<BaseTask>)
        fun navigateToAddTask()
    }

    /**
     * The presenter is responsible for any business logic that does not belong in the UI. Here,
     * that means fetching the tasks, and handling click events that come from the view.
     *
     * This also includes being notified when the view is created and destroyed.
     */
    interface Presenter {
        fun addButtonClicked()
        fun viewCreated()
        fun viewDestroyed()
    }

    /**
     * The model is responsible for being the data source for the feature. This could include pulling
     * data from any remote source or database. The important part is understanding that the data source
     * is not directly tied to the presenter.
     */
    interface Model {
        fun getTasks(): List<BaseTask>
    }
}