package com.example.medical.navgation

import android.provider.ContactsContract.Profile
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medical.composables.Home
import com.example.medical.composables.LoadingScreen
import com.example.medical.composables.LogInScreen
import com.example.medical.composables.ProfileScreen
import com.example.medical.composables.RegisterScreen
import com.example.medical.info.PreferenceManager
import com.example.medical.model.LoginViewModel
import com.example.medical.model.ProfileViewModel
import com.example.medical.repo.LoginRepository

@Composable
fun HomeNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") { Home(navController) }
        composable("Profile") { ProfileScreen(navController) }

    }
}
