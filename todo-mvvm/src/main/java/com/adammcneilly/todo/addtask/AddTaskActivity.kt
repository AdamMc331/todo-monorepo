package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adammcneilly.todo.R
import com.adammcneilly.todo.Task
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        setupViewModel()
        setupSubmitButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel::class.java)

        viewModel.validTask.observe(this, Observer {
            it?.let(this::returnTask)
        })

        viewModel.descriptionError.observe(this, Observer {
            task_description.error = it
        })
    }

    private fun setupSubmitButton() {
        submit_task.setOnClickListener {
            submitTask()
        }
    }

    private fun submitTask() {
        val description = task_description.text.toString()
        viewModel.submitTask(Task(description))
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