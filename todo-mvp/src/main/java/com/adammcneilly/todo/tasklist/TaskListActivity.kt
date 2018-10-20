package com.adammcneilly.todo.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.TaskRepository
import com.adammcneilly.todo_core.BaseTask
import com.adammcneilly.todo_core.BaseTaskAdapter
import com.adammcneilly.todo_core.BaseTaskListActivity

/**
 * An implementation of our [TaskListContract.View] which is responsible for all UI functions related
 * to displaying a list of tasks.
 */
class TaskListActivity : BaseTaskListActivity(), TaskListContract.View {
    private val taskAdapter = BaseTaskAdapter()
    private val presenter = TaskListPresenter(this, TaskRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeRecyclerView()
        initializeFAB()

        presenter.viewCreated()
    }

    private fun initializeFAB() {
        fab.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val description = data?.getStringExtra(AddTaskActivity.DESCRIPTION_KEY).orEmpty()
            val newTask = BaseTask(description)
            taskAdapter.tasks += newTask
        }
    }

    private fun initializeRecyclerView() {
        taskList.adapter = taskAdapter
        taskList.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToAddTask() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    override fun showTasks(tasks: List<BaseTask>) {
        taskAdapter.tasks = tasks
    }

    companion object {
        private const val ADD_TASK_REQUEST = 0
    }
}
