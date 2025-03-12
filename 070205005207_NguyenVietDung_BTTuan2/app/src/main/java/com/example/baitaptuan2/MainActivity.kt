package com.example.baitaptuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.baitaptuan2.ui.screens.BookDetailScreen
import com.example.baitaptuan2.ui.screens.HomeScreen
import com.example.baitaptuan2.ui.screens.LoginScreen
import com.example.baitaptuan2.ui.theme.BaiTapTuan2Theme
import com.example.baitaptuan2.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val viewModel: LibraryViewModel by viewModels()
        
        setContent {
            BaiTapTuan2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentScreen by viewModel.currentScreen
                    val currentUser by viewModel.currentUser
                    val users by viewModel.users
                    val books by viewModel.books
                    val selectedBook by viewModel.selectedBook
                    
                    when (currentScreen) {
                        LibraryViewModel.Screen.LOGIN -> {
                            LoginScreen(
                                users = users,
                                onLogin = { userName -> viewModel.login(userName) }
                            )
                        }
                        LibraryViewModel.Screen.HOME -> {
                            currentUser?.let { user ->
                                HomeScreen(
                                    currentUser = user,
                                    books = books,
                                    users = users,
                                    onBorrowBook = { bookId -> viewModel.borrowBook(bookId) },
                                    onChangeUser = { userId -> viewModel.changeUser(userId) },
                                    onViewBookDetail = { bookId -> viewModel.selectBook(bookId) })
                            }
                        }
                        LibraryViewModel.Screen.BOOK_DETAIL -> {
                            selectedBook?.let { book ->
                                BookDetailScreen(
                                    book = book,
                                    currentUser = currentUser,
                                    onBorrowBook = { bookId -> 
                                        viewModel.borrowBook(bookId)
                                        viewModel.goBack()
                                    },
                                    onGoBack = { viewModel.goBack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}