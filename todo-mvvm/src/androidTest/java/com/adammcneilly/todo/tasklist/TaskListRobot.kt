package com.adammcneilly.todo.tasklist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.adammcneilly.todo.R
import androidx.test.espresso.matcher.ViewMatchers.withId

class TaskListRobot {
    fun add(): TaskListRobot {
        onView(ADD_BUTTON_MATCHER).perform(click())
        return this
    }

    fun assertDisplayed(): TaskListRobot {
        onView(TASK_LIST_LAYOUT_MATCHER).check(matches(isDisplayed()))
        return this
    }

    companion object {
        private val ADD_BUTTON_MATCHER = withId(R.id.fab)
        private val TASK_LIST_LAYOUT_MATCHER = withId(R.id.task_list_layout)
    }
}