package com.example.medical.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medical.composables.AllReports
import com.example.medical.composables.CreateReportScreen
import com.example.medical.composables.Home
import com.example.medical.composables.LoadingScreen
import com.example.medical.composables.LogInScreen
import com.example.medical.composables.ProfileScreen
import com.example.medical.composables.PrototypeMap
import com.example.medical.composables.RegisterScreen
import com.example.medical.info.PreferenceManager
import com.example.medical.model.CreateReportViewModel
import com.example.medical.model.LoginViewModel
import com.example.medical.model.ReportsViewModel
import com.example.medical.repo.LoginRepository
import com.example.medical.repo.repo_impl.AllReportImp
import com.example.medical.repo.repo_impl.CreateRepoImp


@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "PrototypeMap"
    ) {
        composable("Loading") { LoadingScreen(navController) }
        composable("LogIn") {
            LogInScreen(
                viewModel = LoginViewModel(LoginRepository(), PreferenceManager(context)),
                navController
            )
        }
        composable("Register") { RegisterScreen(navController) }
        composable("PrototypeMap") {
            PrototypeMap(
                LoginViewModel(
                    LoginRepository(),
                    PreferenceManager(context)
                ), navController
            )
        }
        composable("Home") { Home(navController) }
        composable("Profile") { ProfileScreen(navController) }
        composable("Reports") { AllReports(navController) }
        composable("CreateReports") {
            CreateReportScreen(
                navController, CreateReportViewModel(
                    CreateRepoImp()
                )
            )
        }
    }
}
