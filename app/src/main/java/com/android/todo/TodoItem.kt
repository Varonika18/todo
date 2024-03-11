package com.android.todo

data class TodoItem(
    val id: Long,
    val task: String,
    val time: String,
    val date: String,

    var isCompleted: Boolean = false,
   // val expirationTimeMillis: Long
)

