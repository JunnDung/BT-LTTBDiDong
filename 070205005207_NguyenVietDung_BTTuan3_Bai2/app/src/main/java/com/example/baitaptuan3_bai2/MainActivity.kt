package com.example.baitaptuan3_bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baitaptuan3_bai2.ui.OnboardingScreen
import com.example.baitaptuan3_bai2.ui.SplashScreen
import com.example.baitaptuan3_bai2.ui.theme.BaiTapTuan3_Bai2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapTuan3_Bai2Theme {
                var currentScreen by remember { mutableStateOf(Screen.Splash) }
                
                when (currentScreen) {
                    Screen.Splash -> {
                        SplashScreen(onNavigateToMain = { currentScreen = Screen.Onboarding })
                    }
                    Screen.Onboarding -> {
                        OnboardingScreen(
                            onNextClick = { currentScreen = Screen.Main },
                            onSkipClick = { currentScreen = Screen.Main }
                        )
                    }
                    Screen.Main -> {
                        MainScreen()
                    }
                }
            }
        }
    }
}

enum class Screen {
    Splash,
    Onboarding,
    Main
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaiTapTuan3_Bai2Theme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BaiTapTuan3_Bai2Theme {
        MainScreen()
    }
}