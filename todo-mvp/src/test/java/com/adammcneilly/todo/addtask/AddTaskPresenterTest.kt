package com.adammcneilly.todo.addtask

import com.adammcneilly.todo_core.BaseTask
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.internal.verification.Times

class AddTaskPresenterTest {
    private val mockView = mock(AddTaskContract.View::class.java)
    private val presenter = AddTaskPresenter(mockView)

    @Test
    fun submitButtonClickedForValidTask() {
        val validTask = BaseTask("Valid")

        whenever(mockView.getTask()).thenReturn(validTask)
        presenter.submitButtonClicked()
        verify(mockView, Times(1)).returnWithTask(validTask)
    }

    @Test
    fun submitButtonClickedForInvalidTask() {
        val invalidTask = BaseTask("")

        whenever(mockView.getTask()).thenReturn(invalidTask)
        presenter.submitButtonClicked()
        verify(mockView, Times(1)).showInvalidDescriptionError()
    }

    @Test
    fun validateTask() {
        val taskWithDescription = BaseTask("valid")
        val taskWithoutDescription = BaseTask("")

        assertTrue(presenter.validateTask(taskWithDescription))
        assertFalse(presenter.validateTask(taskWithoutDescription))

    }
}