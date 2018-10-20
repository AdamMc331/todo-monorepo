package com.adammcneilly.todo.data

import com.adammcneilly.todo_core.BaseTask

/**
 * The data source for getting tasks. In a real application, we may consider a database or remote
 * server here, but for ease of demonstration we supply some dummy tasks.
 */
class TaskRepository {

    fun getItems(): List<BaseTask> {
        return listOf(
                BaseTask("Sample task 1"),
                BaseTask("Sample task 2"),
                BaseTask("Sample task 3"),
                BaseTask("Sample task 4"),
                BaseTask("Sample task 5"),
                BaseTask("Sample task 6"),
                BaseTask("Sample task 7"),
                BaseTask("Sample task 8"),
                BaseTask("Sample task 9"),
                BaseTask("Sample task 10")
        )
    }
}