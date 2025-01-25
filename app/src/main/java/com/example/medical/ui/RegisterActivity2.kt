package com.example.medical.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.medical.navgation.AppNavigation
import com.example.medical.ui.ui.theme.MedicalTheme

class RegisterActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicalTheme {
                AppNavigation()
            }
        }
    }
}
