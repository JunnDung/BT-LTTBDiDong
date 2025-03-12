package com.example.baitaptuan2.repository

import com.example.baitaptuan2.model.Book
import com.example.baitaptuan2.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryRepository {
    // Danh sách sách
    private val _books = MutableStateFlow<List<Book>>(listOf())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    // Danh sách người dùng
    private val _users = MutableStateFlow<List<User>>(listOf())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    // Thêm sách mới
    fun addBook(book: Book) {
        _books.update { currentBooks ->
            currentBooks + book
        }
    }

    // Thêm người dùng mới
    fun addUser(user: User) {
        _users.update { currentUsers ->
            currentUsers + user
        }
    }

    // Khởi tạo dữ liệu mẫu
    fun initSampleData() {
        // Thêm sách mẫu
        addBook(Book("1", "Sách 01"))
        addBook(Book("2", "Sách 02"))
        
        // Thêm người dùng mẫu
        addUser(User("1", "Nguyễn Văn A"))
    }
} 