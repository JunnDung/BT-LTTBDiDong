package com.example.baitaptuan2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan2.model.Book
import com.example.baitaptuan2.model.User

@Composable
fun HomeScreen(
    currentUser: User,
    books: List<Book>,
    users: List<User> = emptyList(),
    onBorrowBook: (String) -> Unit,
    onChangeUser: (String) -> Unit = {},
    onViewBookDetail: (String) -> Unit = {}
) {
    val selectedBooks = remember { mutableStateListOf<String>() }
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Thanh tiêu đề
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0D47A1))
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        
        // Nội dung chính
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Nhân viên
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nhân viên",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = currentUser.name,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    
                    Button(
                        onClick = { expanded = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)),
                        modifier = Modifier.height(56.dp)
                    ) {
                        Text("Đổi")
                    }
                }
                
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    users.forEach { user ->
                        DropdownMenuItem(
                            text = { Text(user.name) },
                            onClick = {
                                onChangeUser(user.id)
                                expanded = false
                            }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Danh sách sách
            Text(
                text = "Danh sách sách",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            books.forEach { book ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(0xFFE91E63), RoundedCornerShape(2.dp))
                                .clickable {
                                    if (selectedBooks.contains(book.id)) {
                                        selectedBooks.remove(book.id)
                                    } else {
                                        selectedBooks.add(book.id)
                                    }
                                }
                        ) {
                            if (selectedBooks.contains(book.id)) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        
                        Text(
                            text = book.title,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                                .clickable { onViewBookDetail(book.id) }
                        )
                        
                        IconButton(
                            onClick = { onViewBookDetail(book.id) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Xem chi tiết",
                                tint = Color(0xFF0D47A1),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Nút Thêm
            Button(
                onClick = { 
                    selectedBooks.forEach { bookId ->
                        onBorrowBook(bookId)
                    }
                    selectedBooks.clear()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Thêm")
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Thanh điều hướng dưới
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Quản lý",
                    tint = Color(0xFF4285F4),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Quản lý",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF4285F4)
                )
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = "DS Sách",
                    tint = Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "DS Sách",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Nhân viên",
                    tint = Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Nhân viên",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }
} 