package com.example.baitaptuan3_bai2.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val color: Long = 0xFF90CAF9
) 