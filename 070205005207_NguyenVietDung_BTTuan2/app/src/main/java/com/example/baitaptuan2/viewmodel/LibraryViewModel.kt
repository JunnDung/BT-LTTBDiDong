package com.example.baitaptuan2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.baitaptuan2.model.Book
import com.example.baitaptuan2.model.User
import com.example.baitaptuan2.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val repository = LibraryRepository()

    // Enum cho các màn hình
    enum class Screen {
        LOGIN,
        HOME,
        BOOK_DETAIL
    }

    // Trạng thái màn hình hiện tại
    private val _currentScreen = mutableStateOf(Screen.LOGIN)
    val currentScreen: State<Screen> = _currentScreen

    // Danh sách người dùng
    private val _users = mutableStateOf(
        listOf(
            User(id = "1", name = "Nguyễn Văn A"),
            User(id = "2", name = "Trần Thị B"),
            User(id = "3", name = "Lê Văn C")
        )
    )
    val users: State<List<User>> = _users

    // Người dùng hiện tại
    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser: State<User?> = _currentUser

    // Sách được chọn để xem chi tiết
    private val _selectedBook = mutableStateOf<Book?>(null)
    val selectedBook: State<Book?> = _selectedBook

    // Danh sách sách
    private val _books = mutableStateOf(
        listOf(
            Book(
                id = "1",
                title = "Sách 01",
                author = "Nguyễn Nhật Ánh",
                description = "Một cuốn sách hay về tuổi thơ và những kỷ niệm đẹp.",
                publishYear = "2020",
                borrowedBy = null
            ),
            Book(
                id = "2",
                title = "Sách 02",
                author = "Tô Hoài",
                description = "Tác phẩm kinh điển của văn học Việt Nam.",
                publishYear = "2018",
                borrowedBy = null
            ),
            Book(
                id = "3",
                title = "Sách 03",
                author = "Nam Cao",
                description = "Một tác phẩm sâu sắc về xã hội Việt Nam.",
                publishYear = "2019",
                borrowedBy = null
            )
        )
    )
    val books: State<List<Book>> = _books

    // Khởi tạo dữ liệu
    init {
        repository.initSampleData()
    }

    // Đăng nhập
    fun login(userName: String) {
        val user = _users.value.find { it.name == userName }
        if (user != null) {
            _currentUser.value = user
            _currentScreen.value = Screen.HOME
        }
    }

    // Đổi nhân viên
    fun changeUser(userId: String) {
        val user = _users.value.find { it.id == userId }
        if (user != null) {
            _currentUser.value = user
        }
    }

    // Chọn sách để xem chi tiết
    fun selectBook(bookId: String) {
        val book = _books.value.find { it.id == bookId }
        if (book != null) {
            _selectedBook.value = book
            _currentScreen.value = Screen.BOOK_DETAIL
        }
    }

    // Quay lại màn hình trước
    fun goBack() {
        when (_currentScreen.value) {
            Screen.BOOK_DETAIL -> {
                _selectedBook.value = null
                _currentScreen.value = Screen.HOME
            }

            else -> {}
        }
    }

    // Mượn sách
    fun borrowBook(bookId: String) {
        val currentBooks = _books.value.toMutableList()
        val bookIndex = currentBooks.indexOfFirst { it.id == bookId }

        if (bookIndex != -1 && _currentUser.value != null) {
            val book = currentBooks[bookIndex]
            if (book.borrowedBy == null) {
                currentBooks[bookIndex] = book.copy(borrowedBy = _currentUser.value?.id)
                _books.value = currentBooks
            }
        }
    }
}
