package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.R
import com.adammcneilly.todo.data.Task
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
        val validTask = Task("Valid")

        whenever(mockView.getTask()).thenReturn(validTask)
        presenter.submitButtonClicked()
        verify(mockView, Times(1)).returnWithTask(validTask)
    }

    @Test
    fun submitButtonClickedForInvalidTask() {
        val invalidTask = Task("")

        whenever(mockView.getTask()).thenReturn(invalidTask)
        presenter.submitButtonClicked()
        verify(mockView, Times(1)).showDescriptionError(R.string.description_must_not_be_empty)
    }

    @Test
    fun validateTask() {
        val taskWithDescription = Task("valid")
        val taskWithoutDescription = Task("")

        assertTrue(presenter.validateTask(taskWithDescription))
        assertFalse(presenter.validateTask(taskWithoutDescription))

    }
}