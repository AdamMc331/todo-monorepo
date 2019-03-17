package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.databinding.ActivityAddTaskBinding

/**
 * The activity responsible for showing the UI for adding a task, and passing any events and
 * relevant information to the [viewModel].
 */
class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupSubmitButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddTaskViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.validTask.observe(this, Observer {
            it?.let(this::returnTask)
        })
    }

    private fun setupSubmitButton() {
        binding.submitTask.setOnClickListener {
            val description = binding.taskDescription.text.toString()
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