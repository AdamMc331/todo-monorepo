package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adammcneilly.todo.R
import com.adammcneilly.todo.data.Task

/**
 * The activity responsible for showing the UI for adding a task, and passing any events and
 * relevant information to the [viewModel].
 */
class AddTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: AddTaskViewModel
    private var submitTaskButton: Button? = null
    private var taskDescriptionEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        submitTaskButton = findViewById(R.id.submit_task)
        taskDescriptionEditText = findViewById(R.id.task_description)

        setupViewModel()
        setupSubmitButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel::class.java)

        viewModel.validTask.observe(this, Observer {
            it?.let(this::returnTask)
        })

        viewModel.descriptionError.observe(this, Observer {
            taskDescriptionEditText?.error = it
        })
    }

    private fun setupSubmitButton() {
        submitTaskButton?.setOnClickListener {
            val description = taskDescriptionEditText?.text.toString()
            viewModel.submitWithDescription(description)
        }
    }

    private fun returnTask(task: Task) {
        val intent = Intent()
        intent.putExtra(DESCRIPTION_KEY, task.description)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val DESCRIPTION_KEY = "taskDescription"
    }
}