package com.adammcneilly.todo.addtask

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.adammcneilly.todo.R
import com.adammcneilly.todo.Task
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
        val emptyTask = Task("")
        val validTask = Task("Blah")

        onView(withId(R.id.task_description)).perform(clearText())
        assertEquals(emptyTask, rule.activity?.getTask())

        onView(withId(R.id.task_description)).perform(clearText(), typeText("Blah"), closeSoftKeyboard())
        assertEquals(validTask, rule.activity?.getTask())
    }

    @Test
    fun submitValidTask() {
        onView(withId(R.id.task_description)).perform(clearText(), typeText("New Task"), closeSoftKeyboard())
        onView(withId(R.id.submit_task)).perform(ViewActions.click())

        assertTrue(rule.activity?.isFinishing == true)
    }

    @Test
    fun showInvalidDescriptionError() {
    }

}