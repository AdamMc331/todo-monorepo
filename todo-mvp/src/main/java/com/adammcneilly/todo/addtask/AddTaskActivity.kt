package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adammcneilly.todo.R
import com.adammcneilly.todo.Task
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity(), AddTaskContract.View {
    private val presenter = AddTaskPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        submit_task.setOnClickListener {
            presenter.submitButtonClicked()
        }
    }

    override fun getTask(): Task {
        val description = task_description.text.toString()
        return Task(description)
    }

    override fun submitTask(task: Task) {
        val intent = Intent()
        intent.putExtra(DESCRIPTION_KEY, task.description)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun showInvalidDescriptionError() {
        task_description.error = "Description must not be empty."
    }

    companion object {
        const val DESCRIPTION_KEY = "taskDescription"
    }
}
