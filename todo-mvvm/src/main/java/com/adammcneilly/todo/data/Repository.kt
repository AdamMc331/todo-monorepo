package com.adammcneilly.todo.data

interface Repository<T> {
    fun getItems(): List<T>
}