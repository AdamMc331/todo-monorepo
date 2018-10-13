package com.adammcneilly.todo.tasklist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adammcneilly.todo.Task
import com.adammcneilly.todo.data.Repository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class TaskListViewModelTest {
    @Suppress("UNCHECKED_CAST")
    private val mockRepo = mock(Repository::class.java) as Repository<Task>
    private val viewModel = TaskListViewModel(mockRepo)

    @JvmField
    @Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Test
    fun getTasks() {
        val sampleTasks = listOf(
                Task("Task One")
        )

        whenever(mockRepo.getItems()).thenReturn(sampleTasks)
        viewModel.getTasks()
        assertEquals(sampleTasks, getTasksFromViewModel())
    }

    private fun getTasksFromViewModel(): List<Task> {
        val observer = LoggingObserver<List<Task>>()
        viewModel.tasks.observeForever(observer)
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