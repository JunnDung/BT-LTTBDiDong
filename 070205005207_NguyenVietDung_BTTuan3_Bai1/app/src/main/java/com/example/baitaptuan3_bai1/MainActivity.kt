package com.example.baitaptuan3_bai1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitaptuan3_bai1.ui.theme.BaiTapTuan3_Bai1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan3_Bai1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("components_list") {
            ComponentsListScreen(navController = navController)
        }
        composable("text_detail") {
            TextDetailScreen(navController = navController)
        }
        composable("image_detail") {
            ImageDetailScreen(navController = navController)
        }
        composable("textfield_detail") {
            TextFieldDetailScreen(navController = navController)
        }
        composable("passwordfield_detail") {
            PasswordFieldDetailScreen(navController = navController)
        }
        composable("column_detail") {
            ColumnDetailScreen(navController = navController)
        }
        composable("row_detail") {
            RowDetailScreen(navController = navController)
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            
            Image(
                painter = painterResource(id = R.drawable.jetpack_logo),
                contentDescription = "Jetpack Compose Logo",
                modifier = Modifier
                    .size(width = 216.dp, height = 233.dp)
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Text(
                text = "Jetpack Compose",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Jetpack Compose is a modern UI toolkit for\nbuilding native Android applications using\na declarative programming approach.",
                style = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                ),
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { navController.navigate("components_list") },
                modifier = Modifier
                    .width(315.dp)
                    .padding(bottom = 24.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(
                    "I'm ready",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ComponentsListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "UI Components List",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp),
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF2196F3),
                        textAlign = TextAlign.Center
                    )
                )
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(Color.White)
        ) {
            Text(
                text = "Display",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ComponentItem(
                title = "Text",
                description = "Displays text",
                onClick = { navController.navigate("text_detail") }
            )

            ComponentItem(
                title = "Image",
                description = "Displays an image",
                onClick = { navController.navigate("image_detail") }
            )
            
            Text(
                text = "Input",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ComponentItem(
                title = "TextField",
                description = "Input field for text",
                onClick = { navController.navigate("textfield_detail") }
            )

            ComponentItem(
                title = "PasswordField",
                description = "Input field for passwords",
                onClick = { navController.navigate("passwordfield_detail") }
            )
            
            Text(
                text = "Layout",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ComponentItem(
                title = "Column",
                description = "Arranges elements vertically",
                onClick = { navController.navigate("column_detail") }
            )

            ComponentItem(
                title = "Row",
                description = "Arranges elements horizontally",
                onClick = { navController.navigate("row_detail") }
            )
        }
    }
}

@Composable
fun ComponentItem(
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x4D2196F3))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = description,
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DetailScreenTopBar(navController, "Text Detail")
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "The ",
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 36.sp
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                
                Row(
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "quick ",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                    
                    Text(
                        text = "Brown",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp,
                            color = Color(0xFFCD7F32),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                
                Row(
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "fox j u m p s ",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp
                        )
                    )
                    
                    Text(
                        text = "over",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                
                Row {
                    Text(
                        text = "the ",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp
                        )
                    )
                    
                    Text(
                        text = "lazy",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    
                    Text(
                        text = " dog.",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 36.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ImageDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DetailScreenTopBar(navController, "Image Detail")
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hiển thị hình ảnh trong Compose",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.jetpack_logo),
                    contentDescription = "Jetpack Compose Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Composable
fun TextFieldDetailScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            DetailScreenTopBar(navController, "TextField Detail")
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nhập liệu văn bản với TextField",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Nhập văn bản") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                
                Text(
                    text = "Văn bản đã nhập: $text",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Composable
fun PasswordFieldDetailScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            DetailScreenTopBar(navController, "PasswordField Detail")
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nhập mật khẩu an toàn",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Nhập mật khẩu") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Info else Icons.Filled.Warning,
                                contentDescription = if (passwordVisible) "Ẩn mật khẩu" else "Hiển thị mật khẩu",
                                tint = Color(0xFF2196F3)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                
                Text(
                    text = "Độ mạnh mật khẩu: ${getPasswordStrength(password)}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = when {
                            password.isEmpty() -> Color.Gray
                            password.length < 6 -> Color.Red
                            password.length < 10 -> Color(0xFFFFA500)
                            else -> Color.Green
                        }
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

fun getPasswordStrength(password: String): String {
    return when {
        password.isEmpty() -> "Chưa nhập"
        password.length < 6 -> "Yếu"
        password.length < 10 -> "Trung bình"
        else -> "Mạnh"
    }
}

@Composable
fun ColumnDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DetailScreenTopBar(navController, "Column Detail")
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sắp xếp theo chiều dọc với Column",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .background(Color(0xFFE0E0E0))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Item 1",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF2196F3))
                            .padding(8.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    
                    Text(
                        text = "Item 2",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF4CAF50))
                            .padding(8.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    
                    Text(
                        text = "Item 3",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF44336))
                            .padding(8.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun RowDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            DetailScreenTopBar(navController, "Row Detail")
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sắp xếp theo chiều ngang với Row",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .background(Color(0xFFE0E0E0))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFF2196F3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "1",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFF4CAF50)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "2",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFFF44336)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "3",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
                
                Text(
                    text = "Row với trọng số (weight)",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .height(50.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color(0xFF2196F3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "1x",
                            color = Color.White
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                            .background(Color(0xFF4CAF50)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "2x",
                            color = Color.White
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxHeight()
                            .background(Color(0xFFF44336)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "3x",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreenTopBar(navController: NavController, title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF2196F3),
                modifier = Modifier
                    .clickable { navController.navigateUp() }
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF2196F3)
                )
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    BaiTapTuan3_Bai1Theme {
        WelcomeScreen(rememberNavController())
    }
}