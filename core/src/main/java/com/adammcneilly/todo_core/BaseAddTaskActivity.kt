package com.adammcneilly.todo_core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseAddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
    }
}