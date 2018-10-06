package com.adammcneilly.todo.tasklist

import com.adammcneilly.todo.Task
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.internal.verification.Times

class TaskListPresenterTest {
    private val mockView = mock(TaskListContract.View::class.java)
    private val mockModel = mock(TaskListContract.Model::class.java)
    private val presenter = TaskListPresenter(mockView, mockModel)

    @Test
    fun addButtonClicked() {
        presenter.addButtonClicked()

        verify(mockView, Times(1)).navigateToAddTask()
    }

    @Test
    fun getTasks() {
        val testList = listOf(Task("Test"))

        whenever(mockModel.getTasks()).thenReturn(testList)

        presenter.getTasks()
        verify(mockView, Times(1)).showTasks(testList)
    }
}