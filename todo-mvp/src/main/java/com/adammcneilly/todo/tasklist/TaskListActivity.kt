package com.adammcneilly.todo.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.todo.R
import com.adammcneilly.todo.Task
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.TaskRepository
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity(), TaskListContract.View {
    private val taskAdapter = TaskAdapter()
    private val presenter = TaskListPresenter(this, TaskRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(toolbar)

        initializeRecyclerView()
        presenter.getTasks()

        fab.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
            val newTask = Task(description)
            taskAdapter.tasks += newTask
        }
    }

    private fun initializeRecyclerView() {
        task_list.adapter = taskAdapter
        task_list.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToAddTask() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    override fun showTasks(tasks: List<Task>) {
        taskAdapter.tasks = tasks
    }

    companion object {
        private const val ADD_TASK_REQUEST = 0
    }
}
