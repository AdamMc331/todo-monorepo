package com.adammcneilly.todo.addtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adammcneilly.todo.data.Task
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class AddTaskViewModelTest {
    private val viewModel = AddTaskViewModel()

    @JvmField
    @Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Test
    fun submitValidTask() {
        val testDescription = "Blah"
        val task = Task(testDescription)

        viewModel.submitWithDescription(testDescription)
        assertEquals(task, getValidTask())
    }

    @Test
    fun submitInvalidTask() {
        val testDescription = ""

        viewModel.submitWithDescription(testDescription)
        assertEquals("Description must not be empty.", getDescriptionError())
    }

    private fun getDescriptionError(): String {
        return viewModel.getDescriptionError()
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