package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

class AddTaskRobot {

    fun description(description: String): AddTaskRobot {
        onView(TASK_DESCRIPTION_MATCHER).perform(clearText(), typeText(description), closeSoftKeyboard())
        return this
    }

    fun submit(): AddTaskRobot {
        onView(SUBMIT_TASK_MATCHER).perform(click())
        return this
    }

    fun assertDescriptionError(errorText: String): AddTaskRobot {
        onView(TASK_DESCRIPTION_MATCHER).check(matches(hasErrorText(errorText)))
        return this
    }

    fun assertDisplayed(): AddTaskRobot {
        onView(ADD_TASK_LAYOUT_MATCHER).check(matches(isDisplayed()))
        return this
    }

    companion object {
        private val ADD_TASK_LAYOUT_MATCHER = withId(R.id.add_task_layout)
        private val TASK_DESCRIPTION_MATCHER = withId(R.id.task_description)
        private val SUBMIT_TASK_MATCHER = withId(R.id.submit_task)
    }
}