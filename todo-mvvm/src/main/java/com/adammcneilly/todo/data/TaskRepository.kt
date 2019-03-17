package com.adammcneilly.todo.data

/**
 * The data source for getting tasks. In a real application, we may consider a database or remote
 * server here, but for ease of demonstration we supply some dummy tasks.
 */
open class TaskRepository {

    open fun getItems(): List<Task> {
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