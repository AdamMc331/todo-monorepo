package com.adammcneilly.todo.tasklist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.data.TaskRepository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class TaskListViewModelTest {
    private val mockRepo = mock(TaskRepository::class.java)
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

    @Test
    fun returnFromAddTask() {
        val emptyTask = Task("")
        viewModel.returnedFromAddTask(null)

        assertEquals(emptyTask, getNewTaskFromViewModel())
    }

    @Test
    fun addButtonClicked() {
        viewModel.addButtonClicked()

        assertEquals(NavigationAction.ADD_TASK, getNavigationActionFromViewModel())
    }

    private fun getTasksFromViewModel(): List<Task> {
        val observer = LoggingObserver<List<Task>>()
        viewModel.tasks.observeForever(observer)
        assertNotNull(observer.value)
        return observer.value!!
    }

    private fun getNewTaskFromViewModel(): Task {
        val observer = LoggingObserver<Task>()
        viewModel.newTask.observeForever(observer)
        assertNotNull(observer.value)
        return observer.value!!
    }

    private fun getNavigationActionFromViewModel(): NavigationAction {
        val observer = LoggingObserver<NavigationAction>()
        viewModel.navigationAction.observeForever(observer)
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