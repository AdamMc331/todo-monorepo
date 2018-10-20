package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adammcneilly.todo_core.R
import com.adammcneilly.todo_core.BaseAddTaskActivity
import com.adammcneilly.todo_core.BaseTask
import com.google.android.material.textfield.TextInputEditText

class AddTaskActivity : BaseAddTaskActivity() {
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupSubmitButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel::class.java)

        viewModel.validTask.observe(this, Observer {
            it?.let(this::returnTask)
        })

        viewModel.descriptionError.observe(this, Observer {
            findViewById<TextInputEditText>(R.id.task_description).error = it
        })
    }

    private fun setupSubmitButton() {
        findViewById<Button>(R.id.submit_task).setOnClickListener {
            submitTask()
        }
    }

    private fun submitTask() {
        val description = findViewById<TextInputEditText>(R.id.task_description).text.toString()
        viewModel.submitTask(BaseTask(description))
    }

    private fun returnTask(task: BaseTask) {
        val intent = Intent()
        intent.putExtra(DESCRIPTION_KEY, task.description)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val DESCRIPTION_KEY = "taskDescription"
    }
}