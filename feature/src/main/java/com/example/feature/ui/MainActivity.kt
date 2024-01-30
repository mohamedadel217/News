package com.example.feature.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.model.NewUiModel
import com.example.feature.ui.detail.DetailScreen
import com.example.feature.ui.home.HomeScreen
import com.example.feature.ui.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Content()
            }
        }
    }

    @Composable
    fun Content() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(
                    onClose = {
                        finish()
                    }, onNavigate = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("home") {
                HomeScreen {
                    navController.currentBackStackEntry?.savedStateHandle?.set("newUiModel", it)
                    navController.navigate("details")
                }
            }
            composable("details") {
                val newUiModel = navController.previousBackStackEntry?.savedStateHandle?.get<NewUiModel>("newUiModel")
                newUiModel?.let {
                    DetailScreen(it) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}