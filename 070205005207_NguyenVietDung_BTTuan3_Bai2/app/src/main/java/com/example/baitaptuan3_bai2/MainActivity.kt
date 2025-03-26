package com.example.baitaptuan3_bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.baitaptuan3_bai2.ui.DetailScreen
import com.example.baitaptuan3_bai2.ui.OnboardingScreen
import com.example.baitaptuan3_bai2.ui.SplashScreen
import com.example.baitaptuan3_bai2.ui.TodoScreen
import com.example.baitaptuan3_bai2.ui.theme.BaiTapTuan3_Bai2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            BaiTapTuan3_Bai2Theme {
                var currentScreen by remember { mutableStateOf(Screen.Splash) }
                var selectedTaskTitle by remember { mutableStateOf("") }
                var selectedTaskDescription by remember { mutableStateOf("") }
                var selectedTaskColor by remember { mutableStateOf(0xFFE8BFC9) }

                when (currentScreen) {
                    Screen.Splash -> {
                        SplashScreen { 
                            currentScreen = Screen.Onboarding
                        }
                    }
                    Screen.Onboarding -> {
                        OnboardingScreen(
                            onNextClick = { 
                                currentScreen = Screen.Main
                            },
                            onSkipClick = { 
                                currentScreen = Screen.Main
                            }
                        )
                    }
                    Screen.Main -> {
                        TodoScreen(
                            onTaskClick = { title, description, color ->
                                selectedTaskTitle = title
                                selectedTaskDescription = description
                                selectedTaskColor = color
                                currentScreen = Screen.Detail
                            }
                        )
                    }
                    Screen.Detail -> {
                        DetailScreen(
                            title = selectedTaskTitle,
                            description = selectedTaskDescription,
                            cardColor = selectedTaskColor,
                            onBackClick = {
                                currentScreen = Screen.Main
                            }
                        )
                    }
                }
            }
        }
    }
}

enum class Screen {
    Splash,
    Onboarding,
    Main,
    Detail
}