package com.adammcneilly.todo_core

/**
 * Since many modules will just be using the same model class for displaying a task, we can create a
 * base version rather than repeat it in each module.
 */
data class BaseTask(
        val description: String
)