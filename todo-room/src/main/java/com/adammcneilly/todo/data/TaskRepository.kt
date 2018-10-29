package com.adammcneilly.todo.data

import androidx.lifecycle.LiveData

open class TaskRepository(private val database: TaskDatabase) {
    fun getTasks(): LiveData<List<Task>> {
        return database.taskDao().getAll()
    }

    fun insertTask(task: Task): Long {
        return database.taskDao().insert(task)
    }
}