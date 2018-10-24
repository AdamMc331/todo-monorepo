package com.adammcneilly.todo.addtask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adammcneilly.todo_core.BaseTask
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
        val testDescription = "Blah"
        val task = BaseTask(testDescription)

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
        val observer = LoggingObserver<String>()
        viewModel.descriptionError.observeForever(observer)
        assertNotNull(observer.value)
        return observer.value!!
    }

    private fun getValidTask(): BaseTask {
        val observer = LoggingObserver<BaseTask>()
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