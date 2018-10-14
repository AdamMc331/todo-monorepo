package com.adammcneilly.todo.addtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adammcneilly.todo.Task
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class AddTaskViewModelTest {
    private val viewModel = AddTaskViewModel()

    @JvmField
    @Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Test
    fun submitValidTask() {
        val task = Task("Blah")
        viewModel.submitTask(task)
        assertEquals(task, getValidTask())
    }

    @Test
    fun submitInvalidTask() {
        val task = Task("")
        viewModel.submitTask(task)
        assertEquals("Description must not be empty.", getDescriptionError())
    }

    private fun getDescriptionError(): String {
        val observer = LoggingObserver<String>()
        viewModel.descriptionError.observeForever(observer)
        assertNotNull(observer.value)
        return observer.value!!
    }

    private fun getValidTask(): Task {
        val observer = LoggingObserver<Task>()
        viewModel.validTask.observeForever(observer)
        assertNotNull(observer.value)
        return observer.value!!
    }

    /**
     * simple observer that logs the latest value it receives
     */
    private class LoggingObserver<T> : Observer<T> {
        var value : T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }
}