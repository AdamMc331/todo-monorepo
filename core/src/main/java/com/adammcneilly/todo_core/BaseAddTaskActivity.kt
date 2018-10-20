package com.adammcneilly.todo_core

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

/**
 * This base class sets up the UI for adding a task, and defines any UI components that implementations
 * need to reference. This was just for ease of use as the various modules will all have the same UI,
 * so there was no need to repeat these files across each one.
 */
open class BaseAddTaskActivity : AppCompatActivity() {
    protected val taskDescriptionEditText: TextInputEditText by lazy {
        findViewById<TextInputEditText>(R.id.task_description)
    }

    protected val submitTaskButton: Button by lazy {
        findViewById<Button>(R.id.submit_task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
    }
}