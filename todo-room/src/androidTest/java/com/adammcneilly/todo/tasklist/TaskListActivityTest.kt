package com.adammcneilly.todo.tasklist

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.espresso.Espresso.pressBack
import androidx.test.rule.ActivityTestRule
import com.adammcneilly.todo.R
import com.adammcneilly.todo.addtask.AddTaskRobot
import com.adammcneilly.todo.data.TaskDatabase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TaskListActivityTest {

    @JvmField
    @Rule
    val rule: ActivityTestRule<TaskListActivity> = ActivityTestRule(TaskListActivity::class.java)

    @Before
    fun setup() {
        val database = TaskDatabase.getInMemoryDatabase(rule.activity)
        database.taskDao().deleteAll()
    }

    @Test
    fun navigateToAddTask() {
        TaskListRobot()
                .add()

        AddTaskRobot()
                .assertDisplayed()
    }

    @Test
    fun taskEnteredAddedToList() {
        val initialSize = 0

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
        val initialSize = 0

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