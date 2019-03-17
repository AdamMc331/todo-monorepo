package com.adammcneilly.todo.data

import org.junit.Assert.*
import org.junit.Test

class TaskViewModelTest {
    @Test
    fun testNullTask() {
        val viewModel = TaskViewModel()
        assertEquals("", viewModel.taskDescription)
    }

    @Test
    fun testValidTask() {
        val viewModel = TaskViewModel().apply {
            task = Task("Adam")
        }

        assertEquals("Adam", viewModel.taskDescription)
    }
}