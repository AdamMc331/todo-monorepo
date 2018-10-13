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
import com.adammcneilly.todo.addtask.AddTaskRobot
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TaskListActivityTest {

    @JvmField
    @Rule
    val rule: ActivityTestRule<TaskListActivity> = ActivityTestRule(TaskListActivity::class.java)

    @Test
    fun navigateToAddTask() {
        TaskListRobot()
                .add()

        AddTaskRobot()
                .assertDisplayed()
    }

    @Test
    fun taskEnteredAddedToList() {
        val initialSize = 10 // Size of our default list that shows the dummy items

        val recyclerView = rule.activity?.findViewById<RecyclerView>(R.id.task_list)
        assertEquals(initialSize, recyclerView?.adapter?.itemCount)

        TaskListRobot()
                .add()

        AddTaskRobot()
                .assertDisplayed()
                .description("New Task")
                .submit()

        TaskListRobot()
                .assertDisplayed()

        val newSize = initialSize + 1
        assertEquals(newSize, recyclerView?.adapter?.itemCount)
    }

    @Test
    fun taskNotEnteredKeepsList() {
        val initialSize = 10 // Size of our default list that shows the dummy items

        val recyclerView = rule.activity?.findViewById<RecyclerView>(R.id.task_list)
        assertEquals(initialSize, recyclerView?.adapter?.itemCount)

        TaskListRobot()
                .add()

        AddTaskRobot()
                .assertDisplayed()

        pressBack()

        TaskListRobot()
                .assertDisplayed()

        assertEquals(initialSize, recyclerView?.adapter?.itemCount)
    }
}