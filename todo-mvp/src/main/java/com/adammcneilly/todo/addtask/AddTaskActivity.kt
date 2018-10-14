package com.adammcneilly.todo.addtask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adammcneilly.todo.R
import com.adammcneilly.todo_core.BaseTask
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

    override fun getTask(): BaseTask {
        val description = task_description.text.toString()
        return BaseTask(description)
    }

    override fun submitTask(task: BaseTask) {
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
