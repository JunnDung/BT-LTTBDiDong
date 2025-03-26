package com.example.baitaptuan3_bai2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitaptuan3_bai2.R
import com.example.baitaptuan3_bai2.ui.theme.BaiTapTuan3_Bai2Theme
import java.util.Calendar

@Composable
fun TodoScreen(
    onTaskClick: (title: String, description: String, color: Long) -> Unit = { _, _, _ -> }
) {
    val primaryBlue = Color(0xFF2196F3)
    
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val hasTasks = currentHour % 2 == 0
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar()
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
                    .background(Color.White)
            ) {
                AppHeader()
                Spacer(modifier = Modifier.height(20.dp))
                
                if (hasTasks) {
                    TaskCard(
                        title = "Complete Android Project",
                        description = "Finish the UI, integrate API, and write documentation",
                        status = "In Progress",
                        time = "14:00 2500-03-26",
                        color = Color(0xFFE8BFC9),
                        isChecked = true,
                        onClick = { onTaskClick("Complete Android Project", "Finish the UI, integrate API, and write documentation", 0xFFE8BFC9) }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    TaskCard(
                        title = "Doctor Appointment 2",
                        description = "This task is related to Work. It needs to be completed",
                        status = "Pending",
                        time = "14:00 2500-03-26",
                        color = Color(0xFFE2EFC9),
                        isChecked = true,
                        onClick = { onTaskClick("Doctor Appointment 2", "This task is related to Work. It needs to be completed", 0xFFE2EFC9) }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    TaskCard(
                        title = "Meeting",
                        description = "This task is related to Fitness. It needs to be completed",
                        status = "Pending",
                        time = "14:00 2500-03-26",
                        color = Color(0xFFBFE4F5),
                        isChecked = false,
                        onClick = { onTaskClick("Meeting", "This task is related to Fitness. It needs to be completed", 0xFFBFE4F5) }
                    )
                } else {
                    EmptyTaskScreen()
                }
            }
        }
        
        FloatingActionButton(
            onClick = { /* TODO: Implement add task */ },
            containerColor = primaryBlue,
            shape = CircleShape,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-32).dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Task",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.uth_logo),
                    contentDescription = "UTH Logo",
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.Fit
                )
                
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "SmartTasks",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )
                    
                    Text(
                        text = "A simple and efficient to-do app",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            
            Image(
                painter = painterResource(id = R.drawable.thongbao),
                contentDescription = "Notifications",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun TaskCard(
    title: String,
    description: String,
    status: String,
    time: String,
    color: Color,
    isChecked: Boolean,
    onClick: () -> Unit = {}
) {
    var checked by remember { mutableStateOf(isChecked) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Black,
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.size(28.dp)
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.height(10.dp))
            
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 44.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Status: $status",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
                
                Text(
                    text = time,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun EmptyTaskScreen() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF1F1F1)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.task),
                    contentDescription = "No Tasks",
                    modifier = Modifier
                        .size(112.dp)
                )
            }
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No Tasks Yet!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Stay productive—add something to do",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White)
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { /* TODO: Navigate */ }
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "🏠",
                    fontSize = 24.sp,
                    color = Color(0xFF2196F3)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Home",
                    fontSize = 12.sp,
                    color = Color(0xFF2196F3),
                    textAlign = TextAlign.Center
                )
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { /* TODO: Navigate */ }
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "📅",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Calendar",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.width(56.dp))
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { /* TODO: Navigate */ }
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "📄",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Tasks",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { /* TODO: Navigate */ }
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "⚙️",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Settings",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    BaiTapTuan3_Bai2Theme {
        TodoScreen()
    }
} 