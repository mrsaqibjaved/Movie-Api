package com.example.movieapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.movieapi.ui.theme.MovieApiTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApiTheme {
                val systemUiController: SystemUiController = rememberSystemUiController()
                systemUiController.isStatusBarVisible = false // Status bar
                systemUiController.isNavigationBarVisible = false // Navigation bar
                systemUiController.isSystemBarsVisible = false // Status & Navigation bars

                HomeScreen()
            }
        }
    }
}

