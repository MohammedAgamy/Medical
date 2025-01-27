package com.example.medical.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.medical.composables.PrototypeMap
import com.example.medical.info.PreferenceManager
import com.example.medical.model.LoginViewModel
import com.example.medical.repo.LoginRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrototypeMap(LoginViewModel(LoginRepository() , PreferenceManager(this)))
        }
    }
}


