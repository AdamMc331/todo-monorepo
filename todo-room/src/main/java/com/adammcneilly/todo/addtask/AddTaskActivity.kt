package com.adammcneilly.todo.addtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.R
import com.adammcneilly.todo.data.TaskDatabase
import com.adammcneilly.todo.data.TaskRepository
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    private lateinit var viewModel: AddTaskViewModel

    private val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val database = TaskDatabase.getInMemoryDatabase(this@AddTaskActivity)
            val repository = TaskRepository(database)
            val viewModel = AddTaskViewModel(repository)

            @Suppress("UNCHECKED_CAST")
            return viewModel as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        setupViewModel()
        setupButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddTaskViewModel::class.java)

        viewModel.descriptionError.observe(this, Observer {
            task_description.error = it
        })

        viewModel.navigationAction.observe(this, Observer {
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (it) {
                NavigationAction.FINISH -> finish()
            }
        })
    }

    private fun setupButton() {
        submit_task.setOnClickListener {
            val description = task_description.text.toString()
            viewModel.submitWithDescription(description)
        }
    }
}
