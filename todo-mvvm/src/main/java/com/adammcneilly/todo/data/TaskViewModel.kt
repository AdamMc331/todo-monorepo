package com.adammcneilly.todo.data

import androidx.databinding.BaseObservable

/**
 * A ViewModel for an individual task. This will get applied to each of the rows in the RecyclerView.
 *
 * @see [com.adammcneilly.todo.tasklist.TaskAdapter]
 */
class TaskViewModel : BaseObservable() {
    var task: Task? = null
        set(value) {
            field = value
            notifyChange()
        }

    val taskDescription: String
        get() = task?.description.orEmpty()
}