package com.adammcneilly.todo.addtask

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.todo.NavigationAction
import com.adammcneilly.todo.data.Task
import com.adammcneilly.todo.data.TaskRepository

class AddTaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val navigationAction = MutableLiveData<NavigationAction>()
    val descriptionError = MutableLiveData<String>()

    private val insertCallback: (Long) -> Unit = {
        if (it != -1L) {
            navigationAction.value = NavigationAction.FINISH
        }
    }

    fun submitWithDescription(description: String) {
        val task = Task(description)

        if (validateTask(task)) {
            InsertAsyncTask(repository, insertCallback).execute(task)
        }
    }

    private fun validateTask(task: Task): Boolean {
        return if (task.description.isEmpty()) {
            descriptionError.value = "Description must not be empty."
            false
        } else {
            true
        }
    }

    class InsertAsyncTask(private val repository: TaskRepository, private val callback: (Long) -> Unit) : AsyncTask<Task, Void, Long>() {
        override fun doInBackground(vararg params: Task?): Long {
            return params.firstOrNull()?.let(repository::insertTask) ?: -1
        }

        override fun onPostExecute(result: Long?) {
            result?.let(callback::invoke)
        }
    }
}