package com.adammcneilly.todo.data

import com.adammcneilly.todo.Task
import com.adammcneilly.todo.tasklist.TaskListContract

class TaskRepository : TaskListContract.Model {
    override fun getTasks(): List<Task> {
        return listOf(
                Task("Sample task 1"),
                Task("Sample task 2"),
                Task("Sample task 3"),
                Task("Sample task 4"),
                Task("Sample task 5"),
                Task("Sample task 6"),
                Task("Sample task 7"),
                Task("Sample task 8"),
                Task("Sample task 9"),
                Task("Sample task 10")
        )
    }
}