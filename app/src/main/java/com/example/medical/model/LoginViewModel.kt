package com.example.medical.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medical.data.login_data.LoginInput
import com.example.medical.info.logininf.LoginState
import com.example.medical.info.PreferenceManager
import com.example.medical.repo.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository,
    val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> get() = _state


    private val _isLoggedIn = MutableStateFlow(preferenceManager.isLoggedIn())
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn


    fun logIn() {
        preferenceManager.setLoggedIn(true)
        _isLoggedIn.value = true
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val response = repository.login(LoginInput(email, password))
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    _state.value = LoginState.Success("Welcome, ${loginResponse.first_name}!")
                    Log.d("TAG", _state.value.toString())
                    logIn()

                } else {
                    _state.value = LoginState.Error("Login failed: ${response.message()}")
                    Log.d("TAG", _state.value.toString())
                }
            } catch (e: Exception) {
                _state.value = LoginState.Error("An error occurred: ${e.message}")
                Log.d("TAG", _state.value.toString())

            }
        }
    }



}