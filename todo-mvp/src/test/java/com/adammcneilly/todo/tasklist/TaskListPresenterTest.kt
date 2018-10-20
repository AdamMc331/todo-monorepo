package com.adammcneilly.todo.tasklist

import com.adammcneilly.todo_core.BaseTask
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
    fun viewCreated() {
        val testList = listOf(BaseTask("Test"))

        whenever(mockModel.getTasks()).thenReturn(testList)

        presenter.viewCreated()
        verify(mockView, Times(1)).showTasks(testList)
    }
}