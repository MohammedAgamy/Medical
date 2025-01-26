package com.example.medical.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medical.data.LoginInput
import com.example.medical.info.LoginState
import com.example.medical.repo.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> get() = _state


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val response = repository.login(LoginInput(email, password))
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    _state.value = LoginState.Success("Welcome, ${loginResponse.first_name}!")
                } else {
                    _state.value = LoginState.Error("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _state.value = LoginState.Error("An error occurred: ${e.message}")
            }
        }
    }
}