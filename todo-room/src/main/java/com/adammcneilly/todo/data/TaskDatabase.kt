package com.adammcneilly.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getInMemoryDatabase(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                        TaskDatabase::class.java, "task.db")
                        .build()
            }

            return INSTANCE!!
        }
    }
}
