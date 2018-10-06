package com.adammcneilly.todo.tasklist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.adammcneilly.todo.R
import com.adammcneilly.todo.Task
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TaskListActivityTest {

    @JvmField
    @Rule
    val rule: ActivityTestRule<TaskListActivity> = ActivityTestRule(TaskListActivity::class.java)

    @Test
    fun navigateToAddTask() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.add_task_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun taskEnteredAddedToList() {
        val sampleTasks = listOf(
                Task("One"),
                Task("Two"),
                Task("Three")
        )

        rule.activity?.runOnUiThread {
            rule.activity?.showTasks(sampleTasks)
        }

        val recyclerView = rule.activity?.findViewById<RecyclerView>(R.id.task_list)
        assertEquals(sampleTasks.size, recyclerView?.adapter?.itemCount)

        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.add_task_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.task_description)).perform(clearText(), typeText("New Task"), closeSoftKeyboard())
        onView(withId(R.id.submit_task)).perform(click())

        onView(withId(R.id.task_list_layout)).check(matches(isDisplayed()))

        val newSize = sampleTasks.size + 1
        assertEquals(newSize, recyclerView?.adapter?.itemCount)
    }

    @Test
    fun taskNotEnteredKeepsList() {
        val sampleTasks = listOf(
                Task("One"),
                Task("Two"),
                Task("Three")
        )

        rule.activity?.runOnUiThread {
            rule.activity?.showTasks(sampleTasks)
        }

        val recyclerView = rule.activity?.findViewById<RecyclerView>(R.id.task_list)
        assertEquals(sampleTasks.size, recyclerView?.adapter?.itemCount)

        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.add_task_layout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.task_list_layout)).check(matches(isDisplayed()))

        assertEquals(sampleTasks.size, recyclerView?.adapter?.itemCount)
    }

    @Test
    fun showTasks() {
        val sampleTasks = listOf(
                Task("One"),
                Task("Two"),
                Task("Three")
        )

        rule.activity?.runOnUiThread {
            rule.activity?.showTasks(sampleTasks)
        }

        val recyclerView = rule.activity?.findViewById<RecyclerView>(R.id.task_list)
        assertEquals(sampleTasks.size, recyclerView?.adapter?.itemCount)
    }
}