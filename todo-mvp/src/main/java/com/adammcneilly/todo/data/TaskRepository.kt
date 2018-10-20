package com.adammcneilly.todo.data

import com.adammcneilly.todo.tasklist.TaskListContract
import com.adammcneilly.todo_core.BaseTask

/**
 * This is an implementation of our [TaskListContract.Model] which is the component responsible
 * for being a data source. In many real world applications, this class would fetch from a database
 * or remote server. For this example, we'll stub in a default list.
 */
class TaskRepository : TaskListContract.Model {
    override fun getTasks(): List<BaseTask> {
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