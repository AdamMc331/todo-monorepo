package com.adammcneilly.todo.addtask

import androidx.test.rule.ActivityTestRule
import com.adammcneilly.todo.data.Task
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AddTaskActivityTest {

    @JvmField
    @Rule
    val rule: ActivityTestRule<AddTaskActivity> = ActivityTestRule(AddTaskActivity::class.java)

    @Test
    fun getTask() {
        val testDescription = "Blah"

        AddTaskRobot()
                .description(testDescription)

        val expectedTask = Task(testDescription)
        assertEquals(expectedTask, rule.activity?.getTask())
    }

    @Test
    fun submitValidTask() {
        AddTaskRobot()
                .description("New Task")
                .submit()

        assertTrue(rule.activity?.isFinishing == true)
    }

    @Test
    fun showInvalidDescriptionError() {
        AddTaskRobot()
                .description("")
                .submit()
                .assertDescriptionError("Description must not be empty.")
    }

}