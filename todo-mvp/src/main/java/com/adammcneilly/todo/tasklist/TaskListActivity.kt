package com.adammcneilly.todo.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adammcneilly.todo.R
import com.adammcneilly.todo.addtask.AddTaskActivity
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.data.TaskRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * An implementation of our [TaskListContract.View] which is responsible for all UI functions related
 * to displaying a list of tasks.
 *
 * NOTE: It's not perfect to have this view create the repository that is passed into the [presenter],
 * but since the view is the one responsible for creating the presenter, it was the easiest option
 * for now. If you use a DI framework such as dagger, you wouldn't need to worry about that.
 */
class TaskListActivity : AppCompatActivity(), TaskListContract.View {
    private val taskAdapter = TaskAdapter()
    private val presenter = TaskListPresenter(this, TaskRepository())
    private var fab: FloatingActionButton? = null
    private var taskList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        fab = findViewById(R.id.fab)
        taskList = findViewById(R.id.task_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        initializeRecyclerView()
        initializeFAB()

        presenter.viewCreated()
    }

    private fun initializeFAB() {
        fab?.setOnClickListener {
            presenter.addButtonClicked()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            presenter.returnedFromAddTask(data)
        }
    }

    private fun initializeRecyclerView() {
        taskList?.adapter = taskAdapter
        taskList?.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToAddTask() {
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, ADD_TASK_REQUEST)
    }

    override fun showTasks(tasks: List<Task>) {
        taskAdapter.tasks = tasks
    }

    override fun onDestroy() {
        presenter.viewDestroyed()
        super.onDestroy()
    }

    override fun addTask(task: Task) {
        taskAdapter.tasks += task
    }

    companion object {
        private const val ADD_TASK_REQUEST = 0
    }
}
