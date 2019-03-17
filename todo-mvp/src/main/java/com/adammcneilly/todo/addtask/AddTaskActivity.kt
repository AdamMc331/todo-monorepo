package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.adammcneilly.todo.R
import com.adammcneilly.todo.data.Task

/**
 * This is the activity responsible for adding a task. It implements [AddTaskContract.View] so that
 * it can perform all of the UI functions defined in [AddTaskContract].
 */
class AddTaskActivity : AppCompatActivity(), AddTaskContract.View {
    private val presenter = AddTaskPresenter(this)
    private var submitTaskButton: Button? = null
    private var taskDescriptionEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        submitTaskButton = findViewById(R.id.submit_task)
        taskDescriptionEditText = findViewById(R.id.task_description)

        submitTaskButton?.setOnClickListener {
            presenter.submitButtonClicked()
        }
    }

    override fun getTask(): Task {
        val description = taskDescriptionEditText?.text.toString()
        return Task(description)
    }

    override fun returnWithTask(task: Task) {
        val intent = Intent()
        intent.putExtra(DESCRIPTION_KEY, task.description)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun showInvalidDescriptionError() {
        taskDescriptionEditText?.error = "Description must not be empty."
    }

    companion object {
        const val DESCRIPTION_KEY = "taskDescription"
    }
}
