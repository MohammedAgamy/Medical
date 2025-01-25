package com.example.medical.info

sealed class Screens(route: String) {
    data object Loading : Screens("loading")
    data object Login : Screens("login")
    data object Register : Screens("register")
}