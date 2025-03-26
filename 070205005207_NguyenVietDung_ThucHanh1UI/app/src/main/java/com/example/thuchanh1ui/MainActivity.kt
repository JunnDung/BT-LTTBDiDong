package com.example.thuchanh1ui

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thuchanh1ui.ui.theme.BlueButton
import com.example.thuchanh1ui.ui.theme.ThucHanh1UITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThucHanh1UITheme {
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
    
    NavHost(navController = navController, startDestination = "navigation") {
        composable("navigation") { 
            NavigationScreen(navController = navController)
        }
        composable("lazy_column") { 
            LazyColumnScreen(navController = navController)
        }
        composable("detail") { 
            DetailScreen(navController = navController)
        }
    }
}

@Composable
fun NavigationScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Logo
        Box(
            modifier = Modifier
                .width(216.dp)
                .height(233.dp)
                .offset(x = 80.dp, y = 159.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Navigation Logo",
                modifier = Modifier.fillMaxSize()
            )
        }
        
        // Tiêu đề "Navigation"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 159.dp + 233.dp + 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Navigation",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        
        // Mô tả
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 159.dp + 233.dp + 24.dp + 40.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "is a framework that simplifies the implementation of navigation between different UI components (activities, fragments, or composables) in an app",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 22.sp,
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
        
        // Nút PUSH
        Box(
            modifier = Modifier
                .width(315.dp)
                .height(52.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-70).dp)
        ) {
            Button(
                onClick = { navController.navigate("lazy_column") },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueButton
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "PUSH", 
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun LazyColumnScreen(navController: NavController) {
    // Tạo danh sách 1.000.000 mục
    val items = remember { 
        List(1000000) { index ->
            when {
                index < 9 -> "${String.format("%02d", index + 1)} | The only way to do great work is to love what you do."
                index == 999999 -> "1.000.000 | The only way to do great work is to love what you do."
                else -> "${index + 1} | The only way to do great work is to love what you do."
            }
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // App bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 32.dp)
        ) {
            // Nút Back bên trái
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF2196F3))
                    .clickable { navController.navigate("navigation") }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "<",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            
            // Tiêu đề LazyColumn
            Text(
                text = "LazyColumn",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF2196F3)
            )
        }
        
        // LazyColumn hiển thị tất cả các mục từ 1 đến 1.000.000
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(items) { item ->
                ListItem(item = item, onClick = { navController.navigate("detail") })
            }
        }
    }
}

@Composable
fun ListItem(item: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD6EAFF))
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Text(
                text = item,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(end = 40.dp)
            )
            
            // Nút mũi tên bên phải
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Black)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ">",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // App bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 32.dp)
            ) {
                // Nút Back bên trái
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2196F3))
                        .clickable { navController.navigate("lazy_column") }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "<",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                
                // Tiêu đề Detail
                Text(
                    text = "Detail",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color(0xFF2196F3)
                )
            }
            
            // Trích dẫn nhỏ ở trên
            Text(
                text = "\"The only way to do great work is to love what you do\"",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        
        // Box chứa ảnh trích dẫn
        Box(
            modifier = Modifier
                .width(296.dp)
                .height(444.dp)
                .offset(x = 46.dp, y = 208.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.detail),
                contentDescription = "Quote Image",
                modifier = Modifier.fillMaxSize()
            )
        }
        
        // Nút BACK TO ROOT
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate("navigation") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "BACK TO ROOT",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationScreenPreview() {
    ThucHanh1UITheme {
        NavigationScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumnScreenPreview() {
    ThucHanh1UITheme {
        LazyColumnScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ThucHanh1UITheme {
        DetailScreen(navController = rememberNavController())
    }
}