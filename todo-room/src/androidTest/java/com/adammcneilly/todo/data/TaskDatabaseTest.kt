package com.adammcneilly.todo.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.adammcneilly.todo.tasklist.TaskListActivity
import junit.framework.TestCase.assertEquals
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {
    private lateinit var database: TaskDatabase
    private lateinit var taskDAO: TaskDAO

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val mainActivity = ActivityTestRule<TaskListActivity>(TaskListActivity::class.java)

    @Before
    fun createDB() {
        val context = mainActivity.activity
        database = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java).build()
        taskDAO = database.taskDao()
    }

    @After
    fun teardown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun testInsert() {
        val newTask = Task(description = "New Task")
        val newId = taskDAO.insert(newTask)
        assertEquals(1, newId)
    }

    @Test
    fun readAll() {
        val newTask = Task(description = "New Task")
        val newId = taskDAO.insert(newTask)
        assertEquals(1, newId)

        val tasks = getTasks()
        assertEquals(1, tasks.size)

        val expectedTask = newTask.copy(id = newId)
        assertEquals(expectedTask, tasks.first())
    }

    private fun getTasks(): List<Task> {
        val observer = LoggingObserver<List<Task>>()
        taskDAO.getAll().observeForever(observer)
        Assert.assertNotNull(observer.value)
        return observer.value!!
    }

    /**
     * simple observer that logs the latest value it receives
     */
    private class LoggingObserver<T> : Observer<T> {
        var value : T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }
}