package com.adammcneilly.todo.tasklist

import android.content.Intent
import android.os.Bundle
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.Task

/**
 * The presenter handles the UI logic for displaying a list of tasks.
 *
 * Notice that dependency injection is used to supply a [view] and [model]. This helps enforce a
 * separation of concerns between the components, but also allows for testability so that we can
 * mock these parameters in unit tests.
 */
class TaskListPresenter(
        private var view: TaskListContract.View?,
        private val model: TaskListContract.Model
) : TaskListContract.Presenter {

    private var tasks: List<Task> = emptyList()

    override fun addButtonClicked() {
        view?.navigateToAddTask()
    }

    override fun viewCreated() {
        if (tasks.isEmpty()) {
            tasks = model.getTasks()
            view?.showTasks(tasks)
        }
    }

    override fun viewDestroyed() {
        view = null
    }

    override fun returnedFromAddTask(data: Intent?) {
        val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
        val newTask = Task(description)
        tasks = tasks + newTask
        view?.showTasks(tasks)
    }

    override fun restoreState(bundle: Bundle?) {
        val prevTasks = bundle?.getParcelableArrayList<Task>(TASK_LIST).orEmpty()

        if (prevTasks.isNotEmpty()) {
            tasks = prevTasks
            view?.showTasks(tasks)
        }
    }

    override fun getState(): Bundle {
        return Bundle().apply {
            putParcelableArrayList(TASK_LIST, ArrayList(tasks))
        }
    }

    companion object {
        const val TASK_LIST = "task_list"
    }
}