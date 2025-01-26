package com.example.medical.repo

import com.example.medical.client.RetrofitInstance
import com.example.medical.data.LogInResponse
import com.example.medical.data.LoginInput
import retrofit2.Response

class LoginRepository {

    suspend fun login(loginInput: LoginInput): Response<LogInResponse> {
        return RetrofitInstance.apiService.login(loginInput)
    }
}