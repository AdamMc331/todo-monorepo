package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.adammcneilly.todo_core.BaseAddTaskActivity
import com.adammcneilly.todo_core.BaseTask

/**
 * This is the activity responsible for adding a task. It implements [AddTaskContract.View] so that
 * it can perform all of the UI functions defined in [AddTaskContract].
 */
class AddTaskActivity : BaseAddTaskActivity(), AddTaskContract.View {
    private val presenter = AddTaskPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        submitTaskButton.setOnClickListener {
            presenter.submitButtonClicked()
        }
    }

    override fun getTask(): BaseTask {
        val description = taskDescriptionEditText.text.toString()
        return BaseTask(description)
    }

    override fun returnWithTask(task: BaseTask) {
        val intent = Intent()
        intent.putExtra(DESCRIPTION_KEY, task.description)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun showInvalidDescriptionError() {
        taskDescriptionEditText.error = "Description must not be empty."
    }

    companion object {
        const val DESCRIPTION_KEY = "taskDescription"
    }
}
