package com.adammcneilly.todo

/**
 * A set of navigation routes we could take. In this example, we can only navigate to adding a task,
 * but we've added this class as a way to demonstrate how you might handle this for communication
 * between a view and viewmodel.
 */
enum class NavigationAction {
    ADD_TASK,
    FINISH
}