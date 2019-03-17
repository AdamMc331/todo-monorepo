package com.adammcneilly.todo.addtask

import com.adammcneilly.todo.addtask.AddTaskContract.Presenter
import com.adammcneilly.todo.addtask.AddTaskContract.View
import com.adammcneilly.todo.data.Task

/**
 * In MVP, a contract class can define the required behavior of both a [View] and a [Presenter].
 *
 * In this case, we don't deal with a model interface because we just pass data back through activities.
 * You may want a model here in a real application, though.
 */
class AddTaskContract {

    /**
     * The [View] is only responsible for anything related to the UI.
     */
    interface View {
        /**
         * Retrieves the current [Task] from the UI components that build the task.
         */
        fun getTask(): Task

        /**
         * While this doesn't sound like a [View] related method, in this case we use Intents to
         * pass data between activities, which must happen inside a [View].
         */
        fun returnWithTask(task: Task)

        /**
         * If the user attempts to add a task with an invalid description, we need a way to display
         * that to the user.
         */
        fun showInvalidDescriptionError()
    }

    /**
     * The [Presenter] is responsible for all business logic that is not [View] related.
     */
    interface Presenter {
        /**
         * The [View] will handle the button clicks, but should pass that event to the presenter
         * who is responsible for determining what should happen.
         */
        fun submitButtonClicked()

        /**
         * The presenter is responsible for determining if a given [task] is valid, and whether
         * we can submit it or alert the user.
         */
        fun validateTask(task: Task): Boolean

        fun viewDestroyed()
    }
}