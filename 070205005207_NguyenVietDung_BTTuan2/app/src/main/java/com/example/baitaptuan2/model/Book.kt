package com.example.baitaptuan2.model

data class Book(
    val id: String,
    val title: String,
    val author: String = "Không có tác giả",
    val description: String = "Không có mô tả",
    val publishYear: String = "Không rõ",
    val borrowedBy: String? = null
) 