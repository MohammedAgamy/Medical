package com.example.medical.navgation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medical.composables.LoadingScreen
import com.example.medical.composables.LogInScreen
import com.example.medical.composables.RegisterScreen
import com.example.medical.model.LoginViewModel
import com.example.medical.repo.LoginRepository


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Loading"
    ) {
        composable("Loading") { LoadingScreen(navController) }
        composable("LogIn") {
            LogInScreen(
                viewModel = LoginViewModel(LoginRepository()),
                navController
            )
        }
        composable("Register") { RegisterScreen(navController) }
    }
}
