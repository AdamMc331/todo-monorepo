package com.adammcneilly.todo_core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * This base class sets up the UI for viewing tasks, and defines any UI components that implementations
 * need to reference. This was just for ease of use as the various modules will all have the same UI,
 * so there was no need to repeat these files across each one.
 */
open class BaseTaskListActivity : AppCompatActivity() {

    protected val toolbar: Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    protected val fab: FloatingActionButton by lazy {
        findViewById<FloatingActionButton>(R.id.fab)
    }

    protected val taskList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.task_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(toolbar)
    }
}