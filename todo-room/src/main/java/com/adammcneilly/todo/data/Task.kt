package com.adammcneilly.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
        val description: String,
        @PrimaryKey(autoGenerate = true) val id: Long = 0
)