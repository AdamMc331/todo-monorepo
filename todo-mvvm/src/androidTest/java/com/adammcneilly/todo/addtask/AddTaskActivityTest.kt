package com.adammcneilly.todo.addtask

import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AddTaskActivityTest {

    @JvmField
    @Rule
    val rule: ActivityTestRule<AddTaskActivity> = ActivityTestRule(AddTaskActivity::class.java)

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