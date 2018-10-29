package com.adammcneilly.todo.addtask

import androidx.test.rule.ActivityTestRule
import com.adammcneilly.todo.data.TaskDatabase
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddTaskActivityTest {

    @JvmField
    @Rule
    val rule: ActivityTestRule<AddTaskActivity> = ActivityTestRule(AddTaskActivity::class.java)

    @Before
    fun setup() {
        val database = TaskDatabase.getInMemoryDatabase(rule.activity)
        database.taskDao().deleteAll()
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