package com.example.baitaptuan3_bai2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@Composable
fun OnboardingScreen(
    onNextClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}
) {
    var currentPage by remember { mutableStateOf(0) }
    val totalPages = 3
    val primaryBlue = Color(0xFF2196F3)
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dots
                for (i in 0 until totalPages) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (i == currentPage) primaryBlue else Color.LightGray.copy(alpha = 0.5f))
                    )
                    
                    if (i < totalPages - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            TextButton(
                onClick = onSkipClick,
                modifier = Modifier.padding(0.dp)
            ) {
                Text(
                    text = "skip",
                    color = primaryBlue,
                    fontSize = 16.sp
                )
            }
        }

        when (currentPage) {
            0 -> OnboardingPage1(
                primaryBlue = primaryBlue,
                onNextClick = { currentPage = 1 }
            )
            1 -> OnboardingPage2(
                primaryBlue = primaryBlue,
                onBackClick = { currentPage = 0 },
                onNextClick = { currentPage = 2 }
            )
            2 -> OnboardingPage3(
                primaryBlue = primaryBlue,
                onBackClick = { currentPage = 1 },
                onGetStartedClick = onNextClick
            )
        }
    }
}

@Composable
fun OnboardingPage1(
    primaryBlue: Color,
    onNextClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            painter = painterResource(id = R.drawable.photo1),
            contentDescription = "Time Management",
            modifier = Modifier
                .width(318.dp)
                .height(260.dp),
            contentScale = ContentScale.Fit
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Easy Time Management",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryBlue
            )
        ) {
            Text(
                text = "Next",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun OnboardingPage2(
    primaryBlue: Color,
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            painter = painterResource(id = R.drawable.photo2),
            contentDescription = "Work Effectiveness",
            modifier = Modifier
                .width(318.dp)
                .height(260.dp),
            contentScale = ContentScale.Fit
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Increase Work Effectiveness",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Time management and the determination of more important tasks will give your job statistics better and always improve",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier.size(56.dp)
                )
            }

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .width(260.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryBlue
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun OnboardingPage3(
    primaryBlue: Color,
    onBackClick: () -> Unit = {},
    onGetStartedClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            painter = painterResource(id = R.drawable.photo3),
            contentDescription = "Reminder Notification",
            modifier = Modifier
                .width(318.dp)
                .height(260.dp),
            contentScale = ContentScale.Fit
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Reminder Notification",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier.size(56.dp)
                )
            }

            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .width(260.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryBlue
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    BaiTapTuan3_Bai2Theme {
        OnboardingScreen()
    }
}
