package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.adammcneilly.todo_core.R
import com.adammcneilly.todo_core.BaseAddTaskActivity
import com.adammcneilly.todo_core.BaseTask
import com.google.android.material.textfield.TextInputEditText

class AddTaskActivity : BaseAddTaskActivity(), AddTaskContract.View {
    private val presenter = AddTaskPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.submit_task).setOnClickListener {
            presenter.submitButtonClicked()
        }
    }

    override fun getTask(): BaseTask {
        val description = findViewById<TextInputEditText>(R.id.task_description).text.toString()
        return BaseTask(description)
    }

    override fun submitTask(task: BaseTask) {
        val intent = Intent()
        intent.putExtra(DESCRIPTION_KEY, task.description)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun showInvalidDescriptionError() {
        findViewById<TextInputEditText>(R.id.task_description).error = "Description must not be empty."
    }

    companion object {
        const val DESCRIPTION_KEY = "taskDescription"
    }
}
