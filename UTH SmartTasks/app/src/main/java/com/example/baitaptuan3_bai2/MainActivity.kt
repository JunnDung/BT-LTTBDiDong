package com.example.baitaptuan3_bai2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitaptuan3_bai2.auth.AuthViewModel
import com.example.baitaptuan3_bai2.ui.AddTaskScreen
import com.example.baitaptuan3_bai2.ui.DetailScreen
import com.example.baitaptuan3_bai2.ui.LoginScreen
import com.example.baitaptuan3_bai2.ui.OnboardingScreen
import com.example.baitaptuan3_bai2.ui.ProfileScreen
import com.example.baitaptuan3_bai2.ui.SplashScreen
import com.example.baitaptuan3_bai2.ui.TaskListScreen
import com.example.baitaptuan3_bai2.ui.TodoScreen
import com.example.baitaptuan3_bai2.ui.theme.BaiTapTuan3_Bai2Theme
import com.example.baitaptuan3_bai2.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    
    private lateinit var authViewModel: AuthViewModel
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        authViewModel.handleSignInResult(result.data)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        authViewModel = androidx.lifecycle.ViewModelProvider(this).get(AuthViewModel::class.java)
        
        lifecycleScope.launch {
            authViewModel.currentUser.collectLatest { user ->
                // Có thể xử lý logic dựa trên trạng thái người dùng nếu cần
            }
        }
        
        setContent {
            BaiTapTuan3_Bai2Theme {
                val taskViewModel: TaskViewModel = viewModel()
                
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
                                currentScreen = Screen.Login
                            },
                            onSkipClick = { 
                                currentScreen = Screen.Login
                            }
                        )
                    }
                    Screen.Login -> {
                        LoginScreen(
                            authViewModel = authViewModel,
                            onGoogleSignIn = {
                                val signInIntent = authViewModel.getGoogleSignInClient(this@MainActivity).signInIntent
                                googleSignInLauncher.launch(signInIntent)
                            },
                            onLoginSuccess = {
                                currentScreen = Screen.Main
                            },
                            onProfileClick = {
                                currentScreen = Screen.Profile
                            }
                        )
                    }
                    Screen.Profile -> {
                        ProfileScreen(
                            onBackClick = {
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
                            },
                            onTaskButtonClick = {
                                currentScreen = Screen.TaskList
                            },
                            onAddTaskClick = {
                                currentScreen = Screen.AddTask
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
                    Screen.TaskList -> {
                        TaskListScreen(
                            viewModel = taskViewModel,
                            onAddNewTask = {
                                currentScreen = Screen.AddTask
                            },
                            onBackClick = {
                                currentScreen = Screen.Main
                            },
                            onHomeClick = {
                                currentScreen = Screen.Main
                            },
                            onTaskClick = { title, description, color ->
                                selectedTaskTitle = title
                                selectedTaskDescription = description
                                selectedTaskColor = color
                                currentScreen = Screen.Detail
                            }
                        )
                    }
                    Screen.AddTask -> {
                        AddTaskScreen(
                            viewModel = taskViewModel,
                            onBackClick = {
                                currentScreen = Screen.TaskList
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
    Login,
    Profile,
    Main,
    Detail,
    TaskList,
    AddTask
}