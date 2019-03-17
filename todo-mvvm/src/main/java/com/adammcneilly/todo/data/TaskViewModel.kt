package com.adammcneilly.todo.data

import androidx.databinding.BaseObservable

class TaskViewModel : BaseObservable() {
    var task: Task? = null
        set(value) {
            field = value
            notifyChange()
        }

    val taskDescription: String
        get() = task?.description.orEmpty()
}