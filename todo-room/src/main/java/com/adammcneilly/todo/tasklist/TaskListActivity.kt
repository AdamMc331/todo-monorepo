package com.adammcneilly.todo.tasklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.adammcneilly.todo.R


class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        //setSupportActionBar(toolbar)

    }

}
