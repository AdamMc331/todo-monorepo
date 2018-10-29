package com.adammcneilly.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO {
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Insert
    fun insert(task: Task): Long

    @Query("DELETE FROM task")
    fun deleteAll()
}